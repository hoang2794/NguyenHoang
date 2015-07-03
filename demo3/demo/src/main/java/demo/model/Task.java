package demo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@Entity
@Table(name="task")
public class Task extends Project {
    private String parentid;

    protected Task(){}

    public Task(String projectid,String name,String macty){
        super(projectid,name,macty);
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
