package demo.model;

import javax.persistence.*;
import java.util.*;
/**
 * Created by Quan Do on 6/17/2015.
 */
@Entity
@Table(name = "boss")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String info;
    private String password;

    @Transient
    @OneToMany(mappedBy = "boss", cascade = CascadeType.ALL, targetEntity = Congty.class, fetch = FetchType.LAZY)
    private List<Congty> Listcongty;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Congty> getListcongty() {
        return Listcongty;
    }

    public void setListcongty(List<Congty> listcongty) {
        this.Listcongty = listcongty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
