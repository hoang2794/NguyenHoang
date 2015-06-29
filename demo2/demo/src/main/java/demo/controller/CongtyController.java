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
    @RequestMapping(value ="/get",method= RequestMethod.GET)
    public Congty Get(@RequestParam("macty")String macty) {
       Congty congty   = congtyRepository.findOne(macty);
        return congty;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public Congty ADD(@RequestParam("boss_id")Integer id,
                      @RequestParam("password")String password,
                      @RequestParam("macty")String macty,
                      @RequestParam("name")String name){
        Congty congty = new Congty();
        User user = userRepository.findOne(id);
        if(user.getPassword()==password) {
            if (congtyRepository.exists(macty)==false){
                congty.setMacty(macty);
                congty.setName(name);
                congty.setBossid(id);
                congtyRepository.save(congty);
                return congty;
            }
        }
        return congty;
    }

    @RequestMapping(value="/edit", method = RequestMethod.PUT)
    public Congty Edit(@RequestParam("macty")String macty,
                        @RequestParam("name")String name,
                       @RequestParam("boss_id")Integer id,
                       @RequestParam("password")String password){
        Congty congty= new Congty();
        User user = userRepository.findOne(id);
        if(user.getPassword()==password) {
            congty = congtyRepository.findOne(macty);
            if (userRepository.exists(id)) {
                congty.setName(name);
                congty.setBossid(id);
                congtyRepository.save(congty);
                return congty;
            }
        }
        return congty;
    }

    @RequestMapping(value= "/delete",method = RequestMethod.DELETE)
    private void Del(@RequestParam("macty")String macty){
        Congty congty = congtyRepository.findOne(macty);
        if(congtyRepository.exists(macty)){
            nhanvienRepository.DelNV(macty);
            congtyRepository.delete(congty);
        }
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
            duanRepository.save(duanE);
        }
        congtyRepository.save(congty);
        return congty;
    }
}
