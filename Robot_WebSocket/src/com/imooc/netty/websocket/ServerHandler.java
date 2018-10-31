package com.imooc.netty.websocket;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.io.File;
import java.io.FileInputStream;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            String url = fullHttpRequest.uri();
            System.out.println("请求资源路径: " + url);
            if (url.equals("/favicon.ico")) {

            } else {
                if (url.equals("/client")) {
                    File f = new File("/home/mzh/IdeaProjects/Robot_WebSocket/web/html/WebsocketClient.html");
//                    File f = new File("/home/wq/Robot_WebSocket/web/html/WebsocketClient.html");
                    FileInputStream fin = new FileInputStream(f);
                    byte[] file_bytes = new byte[fin.available()];
                    fin.read(file_bytes);
                    HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(file_bytes));
                    response.headers().set(CONTENT_TYPE, "text/html");
                    response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                    ctx.writeAndFlush(response);
                }else if(url.equals("/server")){
                    File f = new File("/home/mzh/IdeaProjects/Robot_WebSocket/web/html/WebsocketServer.html");
//                    File f = new File("/home/wq/Robot_WebSocket/web/html/WebsocketServer.html");
                    FileInputStream fin = new FileInputStream(f);
                    byte[] file_bytes = new byte[fin.available()];
                    fin.read(file_bytes);
                    HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(file_bytes));
                    response.headers().set(CONTENT_TYPE, "text/html");
                    response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                    ctx.writeAndFlush(response);
                } else if(url.contains("/websocket")) {
                    ctx.fireChannelRead(msg);
                }
            }
        } else if (msg instanceof WebSocketFrame){
            System.out.println("WebSocket类型");
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
