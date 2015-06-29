package demo.controller;

import demo.model.*;
import demo.model.Task;
import demo.model.Nhanvien;
import demo.repository.CongtyRepository;
import demo.repository.DuanRepository;
import demo.repository.NhanvienRepository;
import demo.repository.TaskRepository;
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
    @RequestMapping(name = "/add",method = RequestMethod.GET)
    public Duan Add(@RequestParam("tenda")String TenDA,
                    @RequestParam("mada")String MaDA,
                    @RequestParam("macty")String macty) {
       Duan duan = new Duan();
        if(duanRepository.exists(MaDA)){

        }
        else if(congtyRepository.exists(macty)){
            duan.setMacty(macty);
            duan.setMaDA(MaDA);
            duan.setTenDA(TenDA);
            return duan;
        }
        return duan;
    }

    @RequestMapping(name = "/edit",method = RequestMethod.PUT)
    public Duan Edit(@RequestParam("tenda")String TenDA,
                     @RequestParam("mada")String MaDA,
                     @RequestParam("macty")String macty) {
        Duan duan = duanRepository.findOne(MaDA);
        if(congtyRepository.exists(macty)){
            duan.setTenDA(TenDA);
            duan.setMacty(macty);
            return duan;
        }
        return duan;
    }

    @RequestMapping(name= "/delete", method = RequestMethod.DELETE)
    public void Del(@RequestParam("mada")String MaDA){
        Duan duan = duanRepository.findOne(MaDA);
        taskRepository.DelTask(MaDA);
        duanRepository.delete(duan);
    }

    @RequestMapping(value = "/listnv", method = RequestMethod.GET)
    public Duan List(@RequestParam("mada")String mada){
       Duan duan = duanRepository.findOne(mada);
        duan.setListNV(nhanvienRepository.listnhanvienDA(mada));
        duan.setListTask(taskRepository.listoftask(mada));
        List<Task> taskList = taskRepository.listoftask(mada);
        for(Task taskE : taskList){
            taskE.setTaskChild(taskRepository.listoftaskchild(taskE.getId()));
            taskRepository.save(taskE);
        }
        duanRepository.save(duan);
        return duan;
    }

}

