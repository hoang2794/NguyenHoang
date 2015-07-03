package demo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nguyen Hoang on 19-Jun-15.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "cty")
public class Congty {
    @Id
    @Column(name = "companyid")
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
    private List<CongtyNhanvien> listNV;

    @Transient
    private List<Duan> listDA;


    public List<Duan> getListDA() {
            return listDA;
        }

    public void setListDA(List<Duan> listDA) {
            this.listDA = listDA;
        }

    public List<CongtyNhanvien> getListNV() {
        return listNV;
    }

    public void setListNV(List<CongtyNhanvien> listNV) {
        this.listNV = listNV;
    }

    public String getMacty() {return macty;}

    public void setMacty(String macty) {this.macty = macty;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

}
