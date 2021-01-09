package io.github.ihelin.seven.chat.server.constant;

public enum MsgType {

    //用户登录消息
    EN_MSG_LOGIN,
    //一对一聊天消息
    EN_MSG_CHAT,
    //响应消息
    EN_MSG_ACK,
    //修改昵称
    EN_MSG_MODIFY_NICKNAME,
    //修改个性签名
    EN_MSG_MODIFY_SIGNATURE,
    //获取登录用户的信息
    EN_MSG_GETINFORMATION ,
    //验证旧密码
    EN_MSG_VERIFY_PASSWORD,
    //获取用户的id
    EN_MSG_GET_ID,
    //获取昵称
    EN_MSG_GET_FRIEND,
    //添加好友
    EN_MSG_ADD_FRIEND ,
    //删除好友
    EN_MSG_DEL_FRIEND ,
    //好友在线状态
    EN_MSG_ACTIVE_STATE,
}
