package client.project.netty;

import client.frame.chat.ChatFrame;
import client.frame.chat.Linkmen;
import client.frame.chat.Login;
import client.project.constant.EnMsgType;
import client.util.JsonUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.SynchronousQueue;

public class ClientHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 定义一个同步阻塞队列状态码
     */
    public static SynchronousQueue<Object> queue = new SynchronousQueue<>();

    public static String Nickname;

    public String Signature;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

    }

    /**
     * 客户端接收数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        //解析服务端发送的消息
        ObjectNode jsonNodes = JsonUtils.getObjectNode((String) msg);
        String msgType = jsonNodes.get("msgType").asText();
        if (EnMsgType.EN_MSG_ACK.toString().equals(msgType)) {
            String srcType = jsonNodes.get("srcType").asText();
            if (EnMsgType.EN_MSG_LOGIN.toString().equals(srcType)) {
                //登录操作
                queue.offer(jsonNodes.get("code").asInt());
            }else if(EnMsgType.EN_MSG_GETINFORMATION.toString().equals(srcType)){
                //存取信息
                 Nickname = jsonNodes.get("Nickname").asText();
                 Signature = jsonNodes.get("Signature").asText();
                Linkmen.label_1.setText(Nickname);
                Linkmen.field.setText(Signature);
            }else if (EnMsgType.EN_MSG_CHAT.toString().equals(srcType)){
                //发送端返回消息
                queue.offer(jsonNodes.get("code").asInt());
            }else if (EnMsgType.EN_MSG_GET_ID.toString().equals(srcType)){
                int uid = jsonNodes.get("uid").asInt();
                queue.offer(uid);
            }else if (EnMsgType.EN_MSG_GET_FRIEND.toString().equals(srcType)){
                //获取登录用户的好友
                int count = jsonNodes.get("count").asInt();
                Login.friend = new String[count];
                for ( int i = 0;i<count;i++){
                    Login.friend[i] = jsonNodes.get("res"+i).asText();
                    System.out.println(jsonNodes.get("res"+i));
                }
            }else if (EnMsgType.EN_MSG_ADD_FRIEND.toString().equals(srcType)){
                //添加好友
                queue.offer(jsonNodes.get("code").asInt());
            }else if (EnMsgType.EN_MSG_DEL_FRIEND.toString().equals(srcType)){
                //删除好友
                queue.offer(jsonNodes.get("code").asInt());
            }else if (EnMsgType.EN_MSG_ACTIVE_STATE.toString().equals(srcType)){
                //好友在线状态
                queue.offer(jsonNodes.get("code").asInt());
            }
        }else if (EnMsgType.EN_MSG_VERIFY_PASSWORD.toString().equals(msgType)){
            //修改密码
            int code = 0;
            code = jsonNodes.get("code").asInt();
            queue.offer(code);
        }else if (EnMsgType.EN_MSG_CHAT.toString().equals(msgType)){
            //接收端接受消息  封装朋友昵称
            String message = " "+ jsonNodes.get("message").asText();
            //聊天显示框读取消息
            ChatFrame.sb.append(message+"\n");
            ChatFrame.displayTextPanel.setText(ChatFrame.sb.toString());
        }
    }
}