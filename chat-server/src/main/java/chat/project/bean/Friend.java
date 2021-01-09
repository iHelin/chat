package chat.project.bean;

public class Friend {
    private String user;
    private String linker;
    private Integer uid;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLinker() {
        return linker;
    }

    public void setLinker(String linker) {
        this.linker = linker;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "user='" + user + '\'' +
                ", linker='" + linker + '\'' +
                ", uid=" + uid +
                '}';
    }
}
