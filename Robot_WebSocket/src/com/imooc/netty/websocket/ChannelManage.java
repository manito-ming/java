package com.imooc.netty.websocket;


import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 存储整个工程的全局配置
 * @author liuyazhuang
 *
 */
public class ChannelManage {
	
	/**
	 * 存储每一个客户端接入进来时的channel对象
	 */
	public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	// 读锁
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    //用户的channel
    private static ConcurrentMap<String, Channel> userIdAndChannelMap = new ConcurrentHashMap<>();



    //用户与客服对应关系
    private static ConcurrentHashMap<String,String> relationshipMap = new ConcurrentHashMap<>();

	public static void send(String receiverId, String message, Channel senderChannel) {
	    // 发送肯定是A要给B发，A就是发消息的对象，B可以是人，机器等对象
        try {
            rwLock.readLock().lock();
            // 1.寻找receiverId的channel
            System.out.println(userIdAndChannelMap.keySet());
            Channel receiverChannel = userIdAndChannelMap.get(receiverId);
            if (receiverChannel == null) {
                // 使用发送者的通道告知发送者，你要发的那个人不在线
                senderChannel.writeAndFlush(new TextWebSocketFrame(receiverId + "不在线"));
                return;
            }
            // 2.发送。A给B发，B若要收到消息，其实是通过B的channel给B发消息
            receiverChannel.writeAndFlush(new TextWebSocketFrame(senderChannel.id() + "  发来消息 ===>" + message));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public static boolean hasChannel(String id) {
        Channel channel = userIdAndChannelMap.get(id);
        if (channel == null) {
            return false;
        } else {
            return true;
        }
    }



    public static ConcurrentHashMap<String, String> getRelationshipMap() {
        return relationshipMap;
    }

    public synchronized static ConcurrentMap getConcurrentHashMap(){
	    if(userIdAndChannelMap != null){
	        return userIdAndChannelMap;
        }
        return new ConcurrentHashMap();
    }
}
