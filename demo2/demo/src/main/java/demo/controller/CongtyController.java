package demo.controller;

import demo.model.*;
import demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@RestController
@RequestMapping("/congty")
public class CongtyController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CongtyRepository congtyRepository;

    @Autowired
    NhanvienRepository nhanvienRepository;

    @Autowired
    DuanRepository duanRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    DuanController duanController;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public Congty ADD(@RequestParam("boss_id")Integer id,
                      @RequestParam("password")String password,
                      @RequestParam("macty")String macty,
                      @RequestParam("name")String name){
        Congty congty = new Congty();
        User user = userRepository.Login(id, password);
        if(user!=null){
                congty.setMacty(macty);
                congty.setName(name);
                congty.setBossid(id);
                congtyRepository.save(congty);
                return congty;
        }
        return congty;
    }

    @RequestMapping(value="/edit", method = RequestMethod.PUT)
    public Congty Edit(@RequestParam("macty")String macty,
                       @RequestParam("newmacty")String newmacty,
                        @RequestParam("name")String name,
                       @RequestParam("boss_id")Integer id,
                       @RequestParam("password")String password){
        Congty congty= new Congty();
        User user = userRepository.Login(id,password);
        if(user!=null) {
            congty = congtyRepository.findOne(macty);
            congty.setName(name);
            List<Duan> duanList = duanRepository.listofDA(macty);
            int count = duanList.size();
            for(int i=0;i<=count;i++){
                Duan duan = duanRepository.findOne(duanList.get(i).getMaDA());
                duan.setMacty(newmacty);
                duanRepository.save(duan);
            }
            congty.setMacty(newmacty);
            congtyRepository.save(congty);
            return congty;
        }
        return congty;
    }

    @RequestMapping(value= "/delete",method = RequestMethod.DELETE)
    public void Del(@RequestParam("macty")String macty){
            Congty congty = congtyRepository.findOne(macty);
            nhanvienRepository.DelNV(macty);
            List<Duan> duanList = duanRepository.listofDA(macty);
            int count = duanList.size();
            for(int i =0;i<count;i++){
                duanController.Del(duanList.get(i).getMaDA());
            }
            congtyRepository.delete(congty);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Congty ListNV(@RequestParam("macty")String macty){
        Congty congty = congtyRepository.findOne(macty);
        congty.setListNV(nhanvienRepository.listnhanvienCT(macty));
        congty.setListDA(duanRepository.listofDA(macty));
        List<Duan> duanList = duanRepository.listofDA(macty);
        for(Duan duanE : duanList){
            duanE.setListNV(nhanvienRepository.listnhanvienDA(duanE.getMaDA()));
            duanE.setListTask(taskRepository.listoftask(duanE.getMaDA()));
            List<Task> listtaskchild = taskRepository.listoftask(duanE.getMaDA());
            for(Task taskchild: listtaskchild){
                taskchild.setTaskChild(taskRepository.listoftaskchild(taskchild.getId()));
                taskRepository.save(taskchild);
            }
            duanRepository.save(duanE);
        }
        congtyRepository.save(congty);
        return congty;
    }
}
