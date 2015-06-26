package demo.controller;

import demo.model.Congty;
import demo.model.Nhanvien;
import demo.model.User;
import demo.repository.CongtyRepository;
import demo.repository.UserRepository;
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
    @RequestMapping(value ="/get",method= RequestMethod.GET)
    public Congty Get(@RequestParam("macty")String macty) {
       Congty congty   = congtyRepository.findOne(macty);
        return congty;
    }

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public Congty ADD(@RequestParam("boss_id")Integer id,
                      @RequestParam("macty")String macty,
                      @RequestParam("name")String name){
        User boss = userRepository.findOne(id);
        Congty congty = new Congty();

        congty.setMacty(macty);
        congty.setName(name);
        congty.setBoss(boss);
        congtyRepository.save(congty);
        return congty;
    }

    @RequestMapping(value="/edit", method = RequestMethod.PUT)
    public Congty Edit(@RequestParam("macty")String macty,
                        @RequestParam("name")String name,
                       @RequestParam("boss_id")Integer id){
        Congty congty = congtyRepository.findOne(macty);
        User boss = userRepository.findOne(id);
        boss.getId();

        congty.setName(name);
        congty.setBoss(boss);
        congtyRepository.save(congty);
        return congty;
    }

    @RequestMapping(value= "/delete",method = RequestMethod.DELETE)
    private void Del(@RequestParam("macty")String macty){
        Congty congty = congtyRepository.findOne(macty);
        congtyRepository.delete(congty);
    }

    @RequestMapping(value = "/listnv", method = RequestMethod.GET)
    public List<Nhanvien> list(@RequestParam("macty")String macty){
        List<Nhanvien> listnv = congtyRepository.listofnhanvien(macty);
        int count = listnv.size();
        for(int i=0;i<=count;i++){
            listnv.get(i);
            return listnv;
        }
        return listnv;
    }
}
