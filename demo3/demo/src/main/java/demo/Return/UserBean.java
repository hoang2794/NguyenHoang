package demo.Return;


import demo.model.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
public class UserBean extends BeanBasic {
    private User user;

    private List<User> userList;

    protected UserBean(){}

    public UserBean(User user, Integer resultcode){
        super(resultcode,"UserBean");
        this.user=user;
    }

    public UserBean(List<User> userList, Integer resultcode){
        super(resultcode,"UserBean");
        this.userList=userList;
    }

    public UserBean(Integer resultcode){
        super(resultcode,"UserBean");
        this.user=null;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
