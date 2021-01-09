package io.github.ihelin.seven.chat.server;

//启动服务端
public class ServerMain {

    public static void main(String[] args) {
        int port = 8765;
        new NettyServer().start(port);
    }
}
