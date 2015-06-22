package demo.model;

import javax.annotation.Resource;
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
    private int boss;
    private Nhanvien nhanvien;

    public Nhanvien getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(Nhanvien nhanvien) {
        this.nhanvien = nhanvien;
    }

    @OneToMany(mappedBy = "nhanvien",cascade = CascadeType.ALL,targetEntity = Nhanvien.class,fetch = FetchType.LAZY)
    private List<Nhanvien> listNV;

    public List<Nhanvien> getListNV() {
        return listNV;
    }

    public void setListNV(List<Nhanvien> listNV) {
        this.listNV = listNV;
    }

    public String getMacty() {return macty;}

    public void setMacty(String macty) {this.macty = macty;}

    public String getName() {return name;}

    public void setName(String ten) {this.name = name;}

    public int getBoss() {return boss;}

    public void setBoss(int boss) {this.boss = boss;}
}
