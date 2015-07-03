package demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@Entity
@Table(name = "duan")
public class Duan extends Project{
    private String manager;

    protected Duan(){}

    public Duan(String projectid,String name,String macty){
        super(projectid,name,macty);
        this.manager=null;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }


    @Transient
    private List<Task> listTask;

    @Transient
    private List<Nhanvien> listNV;


    public List<Nhanvien> getListNV() {
        return listNV;
    }

    public void setListNV(List<Nhanvien> listNV) {
        this.listNV = listNV;
    }


    public List<Task> getListTask() {
        return listTask;
    }

    public void setListTask(List<Task> listTask) {
        this.listTask = listTask;
    }

}


