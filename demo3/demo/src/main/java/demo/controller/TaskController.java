package demo.controller;

import demo.Return.ResultCode;
import demo.Return.TaskBean;
import demo.model.Employee;
import demo.model.Task;
import demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Nguyen Hoang on 25-Jun-15.
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    ProjectJpaRepository projectJpaRepository;


    @Autowired
    EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    TaskJpaRepository taskJpaRepository;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public TaskBean Add(@RequestParam("taskname")String name,
                    @RequestParam("projectid")String projectid,
                    @RequestParam("projectparentid")String projectparentid,
                    @RequestParam("parentid")String pid,
                    HttpSession session) {
        Employee employee = (Employee) session.getAttribute("nhanvien");
        if (employee != null) {
            if (projectJpaRepository.findByProjectid(projectparentid) != null) {
                Task task = new Task(projectid, name,projectparentid);
                if (taskJpaRepository.findByProjectid(pid) != null) {
                    task.setParentid(pid);
                }
                taskJpaRepository.save(task);
                return new TaskBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            } else {
                return new TaskBean(ResultCode.RESULT_CODE_PROJECT_DOES_NOT_EXISTS);
            }
        } else {
            return new TaskBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public TaskBean Edit(
                    @RequestParam("taskname")String name,
                    @RequestParam("projectid")String projectid,
                     HttpSession session){
        Employee employee = (Employee) session.getAttribute("nhanvien2");
        if(employee !=null) {
            Task task = taskJpaRepository.findByProjectid(projectid);
            if(task!=null) {
                task.setName(name);
                taskJpaRepository.save(task);
                return new TaskBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new TaskBean(ResultCode.RESULT_CODE_TASK_DOES_NOT_EXISTS);
            }
        }else{
            return new TaskBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value ="/delete", method = RequestMethod.DELETE)
    public TaskBean Del(@RequestParam("projectid")String id,
                    HttpSession session){
        Employee employee = (Employee) session.getAttribute("manager");
        if(employee !=null) {
            Task task = taskJpaRepository.findByProjectid(id);
            if(task!=null){
                taskJpaRepository.deleteByParentid(id);
                taskJpaRepository.delete(task);
                    return new TaskBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new TaskBean(ResultCode.RESULT_CODE_TASK_DOES_NOT_EXISTS);
            }
        }else {
            return new TaskBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value = "/listchild", method = RequestMethod.GET)
    public TaskBean List(@RequestParam("parentid")String pid) {
        Task task = taskJpaRepository.findByProjectid(pid);
        if(task!=null) {
            task.setTaskChild(taskJpaRepository.findByParentid(pid));
            taskJpaRepository.save(task);
            return new TaskBean(task,ResultCode.RESULT_CODE_SUCCESSFUL);
        }else{
            return new TaskBean(ResultCode.RESULT_CODE_TASK_DOES_NOT_EXISTS);
        }
    }
}
