package demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Nguyen Hoang on 19-Jun-15.
 */
@Entity
@Table(name="nhanvien")
public class Nhanvien extends Person {
    private String macty;
    private String projectid;

    protected Nhanvien(){
    }
    public Nhanvien(String id,String name,String password,String salt){
        super(id,name,password,salt);
        this.macty=null;
        this.projectid=null;
    }

    public Nhanvien(String id,String name,String password,String salt,String macty){
        super(id,name,password,salt);
        this.macty=macty;
        this.projectid=null;
    }

    public Nhanvien(String id,String name,String password,String salt,String macty,String projectid){
        super(id,name,password,salt);
        this.macty=macty;
        this.projectid=projectid;
    }
    public String getMacty() {
        return macty;
    }

    public void setMacty(String macty) {
        this.macty = macty;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }
}

