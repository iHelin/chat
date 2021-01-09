package io.github.ihelin.seven.chat.server.project.netty;

import io.github.ihelin.seven.chat.server.project.controller.NettyController;
import io.github.ihelin.seven.chat.server.utils.CacheUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

    }

    /**
     * 接收数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(ctx.channel().remoteAddress()+"连接");
        System.out.println(msg);
        //获取当前通道
        Channel channel = ctx.channel();
        String message = NettyController.processing((String) msg, channel);

        //将消息写回客户端
        if (message != null){
            ctx.writeAndFlush(message);
        }
    }

    /**
     * 用户上线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+":连接");
    }

    /**
     * 用户下线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //删除用户缓存
        Channel channel = ctx.channel();
        CacheUtil.del(channel);
        System.out.println(ctx.channel().remoteAddress()+":下线了");
    }
}