package demo.Return;

import demo.model.Task;

import java.util.List;

/**
 * Created by Nguyen Hoang on 06-Jul-15.
 */
public class TaskBean extends BeanBasic {
    private Task task;

    private List<Task> taskchild;


    public TaskBean(Integer resultcode){
        super(resultcode,"TaskBean");
    }

    public TaskBean(Integer resultcode,List<Task> taskchild){
        super(resultcode,"TaskBean");
        this.taskchild=taskchild;
    }
    public TaskBean(Task task,Integer resultcode){
        super(resultcode,"TaskBean");
        this.task=task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Task> getTaskchild() {
        return taskchild;
    }

    public void setTaskchild(List<Task> taskchild) {
        this.taskchild = taskchild;
    }
}
