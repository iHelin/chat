package io.github.ihelin.seven.chat.server;

import io.github.ihelin.seven.chat.server.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void start(int port) {

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                .group(boss, worker)
                //指定父循环组通道类型
                .channel(NioServerSocketChannel.class)
                //指定子循环组Handler信息
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();
                        //添加channelHandler
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new ServerHandler());
                    }
                });

            //同步启动服务端
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logger.debug("服务端启动，port:{}", port);
            //同步关闭资源
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("服务端异常", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}