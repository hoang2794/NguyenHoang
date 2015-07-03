package demo.controller;

import demo.model.*;
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
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@RestController
@RequestMapping ("/duan")
public class DuanController {
    @Autowired
    DuanJpaRepository duanJpaRepository;

    @Autowired
    CongtyJpaRepository congtyJpaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskJpaRepository taskJpaRepository;

    @Autowired
    NhanvienJpaRepository nhanvienJpaRepository;

    @Autowired
    ProjectJpaRepository projectJpaRepository;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Duan Add(@RequestParam("projectid")String projectid,
                    @RequestParam("name")String name,
                    @RequestParam("macty")String macty,
                    @RequestParam("manager")String manager,
                    HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if(user!=null){
            if(duanJpaRepository.exists(projectid)){
                return null;
            } else if (congtyJpaRepository.exists(macty)) {
                Duan duan  = new Duan(projectid,name,macty);
                if(nhanvienJpaRepository.exists(manager)){
                    duan.setManager(manager);
                }
                duanJpaRepository.save(duan);
                return duan;
            }
        }
        return null;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public Duan Edit(@RequestParam("name")String name,
                     @RequestParam("projectid")String projectid,
                     @RequestParam("newprojectid")String newprojectid,
                     @RequestParam("macty")String macty,
                     HttpSession session) {
        Duan duan = duanJpaRepository.findOne(projectid);
        Nhanvien managerDA = (Nhanvien) session.getAttribute("nhanvien2");
        if(managerDA!=null) {
            if (duan != null) {
                duan.setName(name);
                List<Task> taskList = taskJpaRepository.findByProjectid(projectid);  //tìm task có chung mã dự án
                int count = taskList.size();
                for (int i = 0; i <= count; i++) {
                    Task task = taskJpaRepository.findOne(taskList.get(i).getProjectid());
                    task.setProjectid(newprojectid);  // set mã dự án mới
                    taskJpaRepository.save(task);
                }
                duan.setProjectid(newprojectid);
                duanJpaRepository.save(duan);
                return duan;
            }
        }
        User user = (User) session.getAttribute("abc");
        if(user!=null){
            if(duan!=null) {
                if (user.getId().compareTo(congtyJpaRepository.findOne(macty).getBossid())== 0) {
                    duan.setMacty(macty);
                    duanJpaRepository.save(duan);
                    return duan;
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void Del(@RequestParam("mada")String mada,
                    HttpSession session) {
        User user = (User) session.getAttribute("boss");
        if(user!=null) {
            Duan duan = duanJpaRepository.findOne(mada);
            if (duan != null) {
                taskJpaRepository.deleteByProjectid(mada);
                duanJpaRepository.delete(duan);
            }
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Duan List(@RequestParam("mada")String mada){
            Duan duan = duanJpaRepository.findOne(mada);
        if(duan!=null) {
            duan.setListNV(nhanvienJpaRepository.findByProjectid(mada));
            duan.setListTask(taskJpaRepository.findByProjectid(mada));
            List<Task> taskList = taskJpaRepository.findByProjectid(mada);
            for (Task taskE : taskList) {
                taskE.setTaskChild(taskJpaRepository.findByParentid(taskE.getParentid()));
                taskJpaRepository.save(taskE);
            }
            duanJpaRepository.save(duan);
            return duan;
        }
        return null;
    }

}

