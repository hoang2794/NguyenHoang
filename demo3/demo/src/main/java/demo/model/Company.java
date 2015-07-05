package demo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nguyen Hoang on 19-Jun-15.
 */
@Entity
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "companyid")
    private String macty;
    private String name;
    private Long bossid;

    public Long getBossid() {
        return bossid;
    }

    public void setBossid(Long bossid) {
        this.bossid = bossid;
    }


    @Transient
    private List<CompanyEmployee> listNV;

    @Transient
    private List<CompanyProjector> listDA;

    public List<CompanyProjector> getListDA() {
        return listDA;
    }

    public void setListDA(List<CompanyProjector> listDA) {
        this.listDA = listDA;
    }

    public List<CompanyEmployee> getListNV() {
        return listNV;
    }

    public void setListNV(List<CompanyEmployee> listNV) {
        this.listNV = listNV;
    }

    public String getMacty() {return macty;}

    public void setMacty(String macty) {this.macty = macty;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

}
