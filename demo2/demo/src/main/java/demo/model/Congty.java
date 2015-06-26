package demo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nguyen Hoang on 19-Jun-15.
 */
@Entity
@Table(name = "Cty")
public class Congty {
    @Id
    private String macty;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="boss_id",referencedColumnName = "id")
    private  User boss;

    @Transient
    @OneToMany(mappedBy = "macty",cascade = CascadeType.ALL,targetEntity = Nhanvien.class,fetch = FetchType.LAZY)
    private List<Nhanvien> listNV;

    @Transient
    @OneToMany(mappedBy = "macty", cascade = CascadeType.ALL, targetEntity = Duan.class, fetch = FetchType.LAZY)
    private List<Duan> listDA;


    public List<Duan> getListDA() {
            return listDA;
        }

        public void setListDA(List<Duan> listDA) {
            this.listDA = listDA;
        }

        public List<Nhanvien> getListNV() {
            return listNV;
        }

        public void setListNV(List<Nhanvien> listNV) {
            this.listNV = listNV;
        }
    public User getBoss() {
        return boss;
    }

    public void setBoss(User boss) {
        this.boss = boss;
    }

    public String getMacty() {return macty;}

    public void setMacty(String macty) {this.macty = macty;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

}
