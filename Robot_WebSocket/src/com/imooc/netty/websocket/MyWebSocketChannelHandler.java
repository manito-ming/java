package com.imooc.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 初始化连接时候的各个组件
 * @author liuyazhuang
 *
 */
public class MyWebSocketChannelHandler extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel e) throws Exception {
		e.pipeline().addLast("http-codec", new HttpServerCodec());
		e.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
		e.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
		// 添加具体的处理器。可以addLast（或者addFirst）多个handler，
		// 第一个参数是名字，无具体要求，如果填写null，系统会自动命名。
		e.pipeline().addLast("server",new ServerHandler());
		e.pipeline().addLast("handler", new MyWebSocketHandler());
		/**ChannelPipeline通过使用管道的方式来处理请求
		 * 第一个配置的管道先处理，然后移交给下一个管道来处理，在每个管道处理中
		 * 各个handler可以决定是否继续或中断
		 * ChannelPipeline和ChannelHandler机制类似于Servlet和Filter过滤器{@link ChannelPipeline}
		 * Netty中的事件分为inbound事件和outbound事件。
		 * inbound事件通常由I/O线程触发，例如TCP链路建立事件、链路关闭事件、读事件、异常通知事件等。方法名以file开始{@link ChannelHandlerContext}
		 * outbound事件类似于发送、刷新、断开连接、绑定本地地址等关闭channel
		 */
	}



}
