package top.luoqiz.netty.demo2.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>Title: SocketClient</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description TODO
 * @date 2020/11/26 14:01
 * @since
 */
public class ChatClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap().group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer());

            Channel channel = bootstrap.connect("localhost", 12345).sync().channel();

            BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                channel.writeAndFlush(buf.readLine() + "\r\n");
            }
        } finally {
            bossGroup.shutdownGracefully();
        }


    }
}
