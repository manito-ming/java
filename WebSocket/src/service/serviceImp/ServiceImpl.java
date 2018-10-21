package service.serviceImp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import service.Service;

public class ServiceImpl implements Service {
    ChannelHandlerContext ctx = null;
    FullHttpRequest req = null;

    public ServiceImpl(ChannelHandlerContext ctx, FullHttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public void service(){
        //通过上下文对象以及请求对象 来进行服务分发

        HttpMethod method = req.method();
        String uri = req.uri();
        System.out.println(method);
        System.out.println(uri);
        QueryStringDecoder decoder = new QueryStringDecoder("/hello?recipient=world&x=1;y=2");
        if(method==HttpMethod.GET&&uri.equals("/signup"))
        {
            System.out.println("xxxxxxxxxxxx");
        }

    }

    public void run() {
        service();
    }
}
