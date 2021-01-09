package io.github.ihelin.seven.chat.server;

import io.github.ihelin.seven.chat.server.project.netty.NettyServer;

//启动服务端
public class ServerMain {

    public static void main(String[] args) {
        int port = 8765;
        new NettyServer().start(port);
    }
}
