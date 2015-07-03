package demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by Nguyen Hoang on 19-Jun-15.
 */
@Entity
@Table(name = "cty")
public class Congty {
    @Id
    private String macty;
    private String name;
    private String bossid;

    public String getBossid() {
        return bossid;
    }

    public void setBossid(String bossid) {
        this.bossid = bossid;
    }

    @Transient
    private List<Nhanvien> listNV;

    @Transient
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

    public String getMacty() {return macty;}

    public void setMacty(String macty) {this.macty = macty;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

}
