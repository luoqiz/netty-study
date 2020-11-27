package top.luoqiz.netty.demo1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * <p>Title: TestServerInitializer</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description TODO
 * @date 2020/11/25 22:04
 * @since 1.0
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipline = ch.pipeline();
        pipline.addLast(new HttpServerCodec());
        pipline.addLast(new TestHttpServerHandler());
    }
}
