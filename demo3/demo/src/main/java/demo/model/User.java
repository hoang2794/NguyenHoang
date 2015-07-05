package demo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Quan Do on 6/17/2015.
 */
@Entity
public class User extends Person {
    private User(){}

    public User(String name,String password){
        super(name,password);
    }

    @Transient
    private List<Company> listcongty;

    public List<Company> getListcongty() {
        return listcongty;
    }

    public void setListcongty(List<Company> listcongty) {
        this.listcongty = listcongty;
    }

}
