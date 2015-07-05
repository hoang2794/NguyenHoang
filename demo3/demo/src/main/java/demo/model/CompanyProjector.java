package demo.model;

import javax.persistence.*;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */
@Entity
@Table(name="companyprojector")
public class CompanyProjector extends Root {
    private String companyid;
    private String projectid;

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }
}
