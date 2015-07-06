package demo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "project")
public class Project extends RootTime {
    protected Project(){}
    private String projectid;
    private String name;
    @Transient
    private List<Task> listTask;

    @Transient
    private List<ProjectEmployee> listNV;

    public Project(String projectid,String name){
        this.projectid=projectid;
        this.name=name;
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


    public List<ProjectEmployee> getListNV() {
        return listNV;
    }

    public void setListNV(List<ProjectEmployee> listNV) {
        this.listNV = listNV;
    }

    public List<Task> getListTask() {
        return listTask;
    }

    public void setListTask(List<Task> listTask) {
        this.listTask = listTask;
    }

}
