package demo.controller;

import demo.model.*;
import demo.model.Task;
import demo.model.Nhanvien;
import demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@RestController
@RequestMapping ("/duan")
public class DuanController {
    @Autowired
    DuanRepository duanRepository;

    @Autowired
    CongtyRepository congtyRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    NhanvienRepository nhanvienRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Duan Add(@RequestParam("bossid")Integer id,
                    @RequestParam("password")String password,
                    @RequestParam("tenda")String TenDA,
                    @RequestParam("mada")String MaDA,
                    @RequestParam("macty")String macty,
                    @RequestParam("manager")String manager) {
        Duan duan = new Duan();
        User user = userRepository.Login(id, password);
        if(user!=null){
            if (congtyRepository.exists(macty)) {
                duan.setMacty(macty);
                duan.setMaDA(MaDA);
                duan.setTenDA(TenDA);
                if(nhanvienRepository.exists(manager)){
                    duan.setManager(manager);
                }
                duanRepository.save(duan);
                return duan;
            }
        }
        return duan;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public Duan Edit(@RequestParam("tenda")String TenDA,
                     @RequestParam("mada")String MaDA,
                     @RequestParam("newmada")String newmada,
                     @RequestParam("macty")String macty,
                     @RequestParam("manager")String manager,
                     @RequestParam("password")String pwmanager,
                     @RequestParam("bossid")Integer bossid,
                     @RequestParam("bosspassword")String pwboss) {
        Duan duan = duanRepository.findOne(MaDA);
        Nhanvien managerDA = nhanvienRepository.Login(manager,pwmanager);
        if(managerDA!=null){
            duan.setTenDA(TenDA);
            List<Task> taskList = taskRepository.listoftask(MaDA);
            int count=taskList.size();
            for(int i=0; i<=count;i++){
                Task task = taskRepository.findOne(taskList.get(i).getId());
                task.setMaDA(newmada);
                taskRepository.save(task);
            }
            duan.setMaDA(newmada);
            duanRepository.save(duan);
            return duan;
            }
        User user = userRepository.Login(bossid,pwboss);
        if(user!=null){
            duan.setMacty(macty);
            duanRepository.save(duan);
            return duan;
        }
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void Del(@RequestParam("mada")String MaDA){
        Duan duan = duanRepository.findOne(MaDA);
        taskRepository.DelTask(MaDA);
        duanRepository.delete(duan);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Duan List(@RequestParam("mada")String mada,
                     @RequestParam("bossid")Integer bossid,
                     @RequestParam("bosspassword")String pwboss){
        User user = userRepository.Login(bossid,pwboss);
        if(user!=null) {
            Duan duan = duanRepository.findOne(mada);
            duan.setListNV(nhanvienRepository.listnhanvienDA(mada));
            duan.setListTask(taskRepository.listoftask(mada));
            List<Task> taskList = taskRepository.listoftask(mada);
            for (Task taskE : taskList) {
                taskE.setTaskChild(taskRepository.listoftaskchild(taskE.getId()));
                taskRepository.save(taskE);
            }
            duanRepository.save(duan);
            return duan;
        }
        return null;
    }

}

