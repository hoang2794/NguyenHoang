package demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Project {
    @Id
    private String projectid;
    private String name;
    private String macty;

    protected Project(){}

    public Project(String projectid,String name,String macty){
        this.projectid=projectid;
        this.name=name;
        this.macty=macty;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacty() {
        return macty;
    }

    public void setMacty(String macty) {
        this.macty = macty;
    }
}
