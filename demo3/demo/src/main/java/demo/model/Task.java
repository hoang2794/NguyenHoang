package demo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@Entity
@Table(name="task")
public class Task extends Project {
    private Task(){}
    private String parentid;


    public Task(String projectid,String name){
        super(projectid,name);
    }

    public Task(String projectid,String name,String parentid){
        super(projectid,name);
        this.parentid = parentid;
    }

    @Transient
    private List<Task> TaskChild;

    public List<Task> getTaskChild() {
        return TaskChild;
    }

    public void setTaskChild(List<Task> taskChild) {
        TaskChild = taskChild;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
}
