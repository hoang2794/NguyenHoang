package demo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Quan Do on 6/17/2015.
 */
@Entity
@Table(name = "boss")
public class User extends Person {

    private String macty;
    protected User(){
    }

    public User(String id,String name,String password,String salt){
        super(id,name,password,salt);
        this.macty=null;
    }

    public User(String id, String name,String password, String salt, String macty){
        super(id,name,password,salt);
        this.macty=macty;
    }
    @Transient
    private List<Congty> Listcongty;

    public List<Congty> getListcongty() {
        return Listcongty;
    }

    public void setListcongty(List<Congty> listcongty) {
        this.Listcongty = listcongty;
    }


    public String getMacty() {
        return macty;
    }

    public void setMacty(String macty) {
        this.macty = macty;
    }
}
