package demo.model;

import javax.persistence.*;
import java.util.*;
/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="parent_task_id",referencedColumnName = "id")
    private Task taskParent;
    private List<Task> TaskChild;

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

    public Task getTaskParent() {
        return taskParent;
    }

    public void setTaskParent(Task taskParent) {
        this.taskParent = taskParent;
    }

    public List<Task> getTaskChild() {

        return TaskChild;
    }

    public void setTaskChild(List<Task> taskChild) {
        TaskChild = taskChild;
    }
}
