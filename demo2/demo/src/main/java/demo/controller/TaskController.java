package demo.controller;

import demo.model.Task;
import demo.model.Duan;
import demo.repository.DuanRepository;
import demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nguyen Hoang on 25-Jun-15.
 */
@RestController
@RequestMapping(name="/task")
public class TaskController {
    @Autowired
    DuanRepository duanRepository;

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping(name= "/add",method = RequestMethod.GET)
    public Task Add(@RequestParam("taskid")Integer id,
                    @RequestParam("taskname")String name,
                    @RequestParam("duanid")String duanid,
                    @RequestParam("parentid")Integer pid){
        Duan duan = duanRepository.findOne(duanid);
        Task taskparent = taskRepository.findOne(pid);
        Task task = new Task();

        task.setId(id);
        task.setName(name);
        task.setDuan(duan);
        task.setTaskParent(taskparent);
        taskRepository.save(task);
        return task;
    }

    @RequestMapping(name= "/edit",method = RequestMethod.GET)
    public Task Edit(@RequestParam("taskid")Integer id,
                    @RequestParam("taskname")String name,
                    @RequestParam("duanid")String duanid,
                    @RequestParam("parentid")Integer pid){
        Task task = taskRepository.findOne(id);
        Task taskparent = taskRepository.findOne(pid);
        Duan duan = duanRepository.findOne(duanid);

        task.setName(name);
        task.setDuan(duan);
        task.setTaskParent(taskparent);
        taskRepository.save(task);
        return task;
    }

    @RequestMapping(name="/delete", method = RequestMethod.GET)
    public void Del(@RequestParam("taskid")Integer id){
        Task task = taskRepository.findOne(id);
        taskRepository.delete(task);
    }
}
