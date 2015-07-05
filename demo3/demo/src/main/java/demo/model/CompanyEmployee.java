package demo.model;

import javax.persistence.*;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
@Entity
@Table(name="companyemployee")
public class CompanyEmployee extends Root{

    private String companyid;
    private String employeeid;


    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }
}
