package demo.model;

import javax.persistence.*;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
@Entity
@Table(name="companyemployee")
public class CompanyEmployee extends Root{

    private String companyid;
    private Long employeeid;


    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public Long getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Long employeeid) {
        this.employeeid = employeeid;
    }
}
