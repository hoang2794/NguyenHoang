package demo.Return;


import demo.model.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
public class UserBean extends BeanBasic {
    public Bean bean;
    private List<User> userList;

    protected UserBean(){}

    public UserBean(User user, Integer resultcode){
        super(resultcode,"UserBean");
        this.bean = new Bean(user);
    }

    public UserBean(List<User> userList, Integer resultcode){
        super(resultcode,"UserBean");
        this.userList=userList;
    }

    public UserBean(Integer resultcode){
        super(resultcode,"UserBean");
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
