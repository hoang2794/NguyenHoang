package demo.controller;

import demo.model.Nhanvien;
import demo.model.Task;
import demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    DuanJpaRepository duanJpaRepository;


    @Autowired
    NhanvienJpaRepository nhanvienJpaRepository;

    @Autowired
    TaskJpaRepository taskJpaRepository;

    @RequestMapping(value ="/nvlogin",method= RequestMethod.POST)
    public Integer NVLogin(@RequestParam("manager")String manv,
                           @RequestParam("password")String password,
                           HttpSession session) {
        Nhanvien nhanvien = nhanvienJpaRepository.findOne(manv);
        if(nhanvien!=null) {
            if (BCrypt.checkpw(password, nhanvien.getPassword())) {
                session.setAttribute("manager", nhanvien);
                return 0;
            } else {
                return -1;
            }
        }
        return null;
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Task Add(@RequestParam("taskname")String name,
                    @RequestParam("projectid")String projectid,
                    @RequestParam("macty")String macty,
                    @RequestParam("parentid")String pid,
                    HttpSession session){
        Nhanvien nhanvien = (Nhanvien) session.getAttribute("nhanvien2");
        if(nhanvien!=null){
            if (duanJpaRepository.exists(projectid)) {
                Task task = new Task(projectid,name,macty);
                if (taskJpaRepository.exists(pid)) {
                    task.setParentid(pid);
                }
                taskJpaRepository.save(task);
                return task;
            }
        }
        return null;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public Task Edit(@RequestParam("macty")String macty,
                    @RequestParam("taskname")String name,
                    @RequestParam("projectid")String projectid,
                    @RequestParam("pid")String pid,
                     HttpSession session){
        Nhanvien nhanvien = (Nhanvien) session.getAttribute("nhanvien2");
        if(nhanvien!=null) {
            if(nhanvien.getMacty().compareTo(macty)==0){
                if(nhanvien.getProjectid().compareTo(projectid)==0) {
                    Task task = taskJpaRepository.findOne(projectid);
                    task.setName(name);
                    if (taskJpaRepository.exists(pid)) {
                        task.setParentid(pid);
                    }
                }
            }
        }
        return null;
    }

    @RequestMapping(value ="/delete", method = RequestMethod.DELETE)
    public void Del(@RequestParam("taskid")String id,
                    HttpSession session){
        Nhanvien nhanvien = (Nhanvien) session.getAttribute("manager");
        if(nhanvien!=null) {
            Task task = taskJpaRepository.findOne(id);
            if(task!=null){
                taskJpaRepository.deleteByParentid(id);
                taskJpaRepository.delete(task);
            }
        }
    }

    @RequestMapping(value = "/listchild", method = RequestMethod.GET)
    public Task List(@RequestParam("parentid")String pid) {
        Task task = taskJpaRepository.findOne(pid);
        if(task!=null) {
            task.setTaskChild(taskJpaRepository.findByParentid(pid));
            taskJpaRepository.save(task);
            return task;
        }
        return null;
    }
}
