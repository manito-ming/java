package service.serviceImp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import service.Service;

public class NewServiceImpl implements Service {
    WebSocketFrame wsframe=null;
    ChannelHandlerContext ctx=null;
    public NewServiceImpl(ChannelHandlerContext ctx,WebSocketFrame wsframe ){
        this.ctx=ctx;
        this.wsframe=wsframe;
    }
    @Override
    public void run() {
        service();
    }

    @Override
    public void service() {
        System.out.println("flag4");
        //获取客户端向服务器发送的消息
        TextWebSocketFrame textWebSocketFrame=(TextWebSocketFrame) wsframe;
        String request=textWebSocketFrame.text();
        System.out.println(request);
        String [] str = request.split(" ");

        String xx = "";



        if (str[1].equals("碰到墙")){
                xx =str[0]+":"+ "向左走";
            }
           if (str[1].equals("碰到敌军")){
             xx =str[0]+":"+ "跑";
            }

        //回复客户端
        ctx.channel().writeAndFlush(new TextWebSocketFrame("hello from server:"+xx));
    }
}
