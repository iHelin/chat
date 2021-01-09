package chat.project.dao;

public interface UserDao {

    boolean getInformation(int id,String passwd);

    /**
     * 验证密码
     * @param oldPasswd
     * @param id
     * @return
     */
    boolean verifyPassword(String oldPasswd,Integer id);

    /**
     * 修改密码
     * @param newPasswd
     * @param id
     */
    void modifyPasswd(String newPasswd,Integer id);

    /**
     * 验证是否存在所添加好友的id
     * @param id
     * @return
     */
    boolean verifyExistFriend(Integer id);
}
