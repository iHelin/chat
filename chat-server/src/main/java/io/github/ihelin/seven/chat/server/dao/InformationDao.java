package io.github.ihelin.seven.chat.server.dao;

import io.github.ihelin.seven.chat.server.bean.Information;

public interface InformationDao {

    //获取用户登录信息
    Information getInformation(Integer id);

    //将修改后的签名存储到SQL
    void storeSignature(String signature, Integer id);

    //将修改后的昵称存储到SQL
    void storeNickname(String nickname, Integer id);

    //获取id
    Information nicknameGetId(String nickname);

}
