package demo.Return;


import demo.model.User;

import javax.persistence.Transient;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
public class UserReturn extends ErrorList {
    private User user;

    private List<User> userList;

    protected UserReturn(){}

    public UserReturn(User user,HashMap errorList){
        super(errorList);
        this.user=user;
    }

    public UserReturn(List<User> userList,HashMap errorList){
        super(errorList);
        this.userList=userList;
    }

    public  UserReturn(HashMap errorList){
        super(errorList);
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
