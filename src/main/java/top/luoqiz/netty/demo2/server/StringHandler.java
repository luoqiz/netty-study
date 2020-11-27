package top.luoqiz.netty.demo2.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * <p>Title: StringHandler</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description TODO
 * @date 2020/11/26 13:53
 * @since 1.0
 */
public class StringHandler extends SimpleChannelInboundHandler<String> {

    public static DefaultChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        group.forEach(channel -> {
            if (channel != ctx.channel()) {
                channel.writeAndFlush(String.format("[%s] 发来消息 - %s \n", channel.remoteAddress(), msg));
            } else {
                ctx.channel().writeAndFlush(String.format("[自己] 发来消息 - %s \n", msg));
            }
        });
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        group.writeAndFlush(String.format("[服务器] ： %s  加入聊天 \n", ctx.channel().remoteAddress()));
        group.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        group.writeAndFlush(String.format("[服务器] ： %s  退出聊天 \n", ctx.channel().remoteAddress()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        group.writeAndFlush(String.format("[服务器] ： %s  上线了 \n", ctx.channel().remoteAddress()));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        group.writeAndFlush(String.format("[服务器] ： %s  下线了\n", ctx.channel().remoteAddress()));
    }

}
