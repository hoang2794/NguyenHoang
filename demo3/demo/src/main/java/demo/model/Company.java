package demo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nguyen Hoang on 19-Jun-15.
 */
@Entity
@Table(name = "company")
public class Company extends RootTime {
    private String companyid;
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
    private List<CompanyProject> listDA;

    public List<CompanyProject> getListDA() {
        return listDA;
    }

    public void setListDA(List<CompanyProject> listDA) {
        this.listDA = listDA;
    }

    public List<CompanyEmployee> getListNV() {
        return listNV;
    }

    public void setListNV(List<CompanyEmployee> listNV) {
        this.listNV = listNV;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }
}
