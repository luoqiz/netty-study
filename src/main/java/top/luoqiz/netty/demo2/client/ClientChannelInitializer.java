package top.luoqiz.netty.demo2.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * <p>Title: ClientChannelInitializer</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description TODO
 * @date 2020/11/26 14:17
 * @since
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()))
                .addLast(new StringEncoder())
                .addLast(new StringDecoder())
                .addLast(new SocketClientStringHandle())
        ;
    }
}
