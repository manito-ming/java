package mainServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import service.Service;
import service.serviceImp.NewServiceImpl;
import service.serviceImp.ServiceImpl;

import java.util.HashSet;
import java.util.Set;


public class MyWebSocektHandler extends ChannelInboundHandlerAdapter {
    private WebSocketServerHandshaker handshaker;
    private static int count=0;
    private static Set<String> s = new HashSet<>();
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler added:"+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler removed:"+ctx.channel().id().asLongText());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.add(ctx.channel());
        count++;
        s.add(ctx.channel().id().toString());
        System.out.println("新连接进入:"+ctx.channel().id());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.remove(ctx.channel());
        count--;
        s.remove(ctx.channel().id().toString());
        System.out.println("客户端与服务端链接关闭");
    }
    //接受，处理，响应的方法,服务端处理客户端websocket请求的方法
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest){
            handleHttpRequest(ctx,(FullHttpRequest) msg);
        }else if (msg instanceof WebSocketFrame){
            handleWebSocketFrame(ctx,(WebSocketFrame)msg);
        }
    }
    /*处理客户端向服务端发起http握手请求的业务
     * */
    private void handleHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req){
        //客户端向服务端建立http请求时会带有一些特殊的请求头    所以用 Upgrade的websocket去比较
        if (!req.decoderResult().isSuccess()||(!"websocket".equals(req.headers().get("Upgrade")))){
            sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory=new WebSocketServerHandshakerFactory("ws://localhost:8888/ws",null,false);
        handshaker=webSocketServerHandshakerFactory.newHandshaker(req);
        if (handshaker==null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else {
            //向客户端发送握手，完成握手
            ChannelFuture channelFuture=handshaker.handshake(ctx.channel(),req);
            if (channelFuture.isSuccess()){
                Service service=new ServiceImpl(ctx,req);
                //将上下文和请求发送给业务层启动线程去工作
                new Thread(service).start();
            }
       }
    }

    //服务端向客户端响应消息
    private void handleWebSocketFrame(ChannelHandlerContext ctx,WebSocketFrame wsframe) throws Exception {
        //判断是否关闭websocket指令
        if (wsframe instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(),(CloseWebSocketFrame) wsframe.retain());
            System.out.println("flag1");
            return;
        }
        //判断是否ping消息
        if (wsframe instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(wsframe.content().retain()));
            System.out.println("flag2");
        }
        //判断是否二进制数据
        if (!(wsframe instanceof TextWebSocketFrame)){
            System.out.println("flag3");
            throw new UnsupportedOperationException(String.format("%s frame types not supported",wsframe.getClass().getName()));
        }

        Service service=new NewServiceImpl(ctx,wsframe);
        new Thread(service).start();

//        /*这是用来获取channelid的*/
//        String request = ((TextWebSocketFrame)wsframe).text();
//        System.out.println("服务端收到客户端的消息=====>>"+request);
//        String sb = "";
//        for (String value:s)
//        {
//            sb=sb+value+":";
//        }
//        TextWebSocketFrame tws = new TextWebSocketFrame(ctx.channel().id().toString()+":"+request );
//        String []pp = request.split(":");
//        if (request.equals("id"))
//        {
//            ctx.channel().writeAndFlush(new TextWebSocketFrame(ctx.channel().id().toString()));
//            if (count == 2)
//            {
//                NettyConfig.group.writeAndFlush(new TextWebSocketFrame(sb));
//
//            }
//        }else if (pp[1].equals("aaaa"))
//        {
//            NettyConfig.group.writeAndFlush(new TextWebSocketFrame(pp[0]+";"+"aaaa"+";"+1+"..........."));
//        }


        //群发，服务端向每个连接上来的客户端群发信息

//        ctx.channel().writeAndFlush("ss");


    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res){
        if (res.status().code()!=200){ //请求失败的处理信息
            ByteBuf byteBuf=Unpooled.copiedBuffer(res.status().toString(),CharsetUtil.UTF_8);
            res.content().writeBytes(byteBuf);
            byteBuf.release();
            HttpUtil.setContentLength(res,res.content().readableBytes());
        }
        //服务端向客户端发送数据
        ChannelFuture channelFuture=ctx.channel().writeAndFlush(res);
        if (!HttpUtil.isKeepAlive(req)||res.status().code()!=200){
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
