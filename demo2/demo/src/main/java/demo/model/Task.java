package demo.model;

import javax.persistence.*;
import java.util.*;
/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Name;
    private String MaDA;
    private int task_parent_id;

    public String getMaDA() {
        return MaDA;
    }

    public void setMaDA(String maDA) {
        MaDA = maDA;
    }

    public int getTask_parent_id() {
        return task_parent_id;
    }

    public void setTask_parent_id(int task_parent_id) {
        this.task_parent_id = task_parent_id;
    }

    @Transient
    private List<Task> TaskChild;

    public List<Task> getTaskChild() {
        return TaskChild;
    }

    public void setTaskChild(List<Task> taskChild) {
        TaskChild = taskChild;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
