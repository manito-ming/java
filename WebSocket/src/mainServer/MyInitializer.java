package mainServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("http-codec", new HttpServerCodec());
        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
        pipeline.addLast("http-agra", new HttpObjectAggregator(65535));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
//        pipeline.addLast(new MyFilter());
        pipeline.addLast(new SingleSignUp());
        pipeline.addLast(new MyWebSocektHandler());


    }
}
