package top.luoqiz.netty.demo2.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <p>Title: SocketClientChannelInitalizer</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description TODO
 * @date 2020/11/26 14:09
 * @since
 */
public class SocketClientStringHandle extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(String.format("收到服务器通知： %s", msg));
    }
}
