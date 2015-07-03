package demo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Quan Do on 6/17/2015.
 */
@Entity
public class User extends Person {

    protected User(){
    }

    public User(String name,String password){
        super(name,password);
    }

    @Transient
    private List<Congty> Listcongty;

    public List<Congty> getListcongty() {
        return Listcongty;
    }

    public void setListcongty(List<Congty> listcongty) {
        this.Listcongty = listcongty;
    }

}
