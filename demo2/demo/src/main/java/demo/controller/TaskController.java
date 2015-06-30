package demo.controller;

import demo.model.Nhanvien;
import demo.model.Task;
import demo.model.Duan;
import demo.repository.DuanRepository;
import demo.repository.NhanvienRepository;
import demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nguyen Hoang on 25-Jun-15.
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    DuanRepository duanRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    NhanvienRepository nhanvienRepository;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Task Add(@RequestParam("taskname")String name,
                    @RequestParam("mada")String MaDA,
                    @RequestParam("parentid")Integer pid,
                    @RequestParam("manager")String manager,
                    @RequestParam("password")String password){
        Task task  = new Task();
        Nhanvien nhanvien = nhanvienRepository.Login(manager,password);
        if(nhanvien!=null) {
            if (duanRepository.exists(MaDA)) {
                task.setMaDA(MaDA);
                task.setName(name);
                if (taskRepository.exists(pid)) {
                    task.setTask_parent_id(pid);
                }
                taskRepository.save(task);
                return task;
            }
        }
        return null;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public Task Edit(@RequestParam("taskid")Integer id,
                    @RequestParam("taskname")String name,
                    @RequestParam("mada")String MaDA,
                    @RequestParam("pid")Integer pid){
        Task task = taskRepository.findOne(id);
        if(duanRepository.exists(MaDA)){
            task.setMaDA(MaDA);
            task.setName(name);
            taskRepository.save(task);
            return task;
        }
        if(taskRepository.exists(pid)){
            task.setTask_parent_id(pid);
            taskRepository.save(task);
            return task;
        }
        return null;
    }

    @RequestMapping(value ="/delete", method = RequestMethod.DELETE)
    public void Del(@RequestParam("taskid")Integer id,
                    @RequestParam("manager")String manager,
                    @RequestParam("password")String password){
        Nhanvien nhanvien = nhanvienRepository.Login(manager,password);
        if(nhanvien!=null) {
            Task task = taskRepository.findOne(id);
            if (task.getTask_parent_id() == id) {
                taskRepository.DelChild(id);
            }
            taskRepository.delete(task);
        }
    }

    @RequestMapping(value = "/listchild", method = RequestMethod.GET)
    public Task List(@RequestParam("parentid")Integer pid) {
        Task task = taskRepository.findOne(pid);
        task.setTaskChild(taskRepository.listoftaskchild(pid));
        taskRepository.save(task);
        return task;
    }
}
