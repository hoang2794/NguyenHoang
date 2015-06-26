package demo.controller;

import demo.model.*;
import demo.model.Task;
import demo.repository.CongtyRepository;
import demo.repository.DuanRepository;
import demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(name = "/add",method = RequestMethod.GET)
    public Duan Add(@RequestParam("tenda")String TenDA,
                    @RequestParam("mada")String MaDA,
                    @RequestParam("macty")String macty) {
        Congty congty = congtyRepository.findOne(macty);
        congty.getMacty();
        Duan duan = new Duan();
        duan.setMaDA(MaDA);
        duan.setTenDA(TenDA);
        duan.setDuan(congty);
        duanRepository.save(duan);
        return duan;
    }

    @RequestMapping(name = "/edit",method = RequestMethod.PUT)
    public Duan Edit(@RequestParam("tenda")String TenDA,
                     @RequestParam("mada")String MaDA,
                     @RequestParam("macty")String macty) {
        Congty congty = congtyRepository.findOne(macty);
        congty.getMacty();
        Duan duan = duanRepository.findOne(MaDA);
        duan.setTenDA(TenDA);
        duan.setDuan(congty);
        duanRepository.save(duan);
        return duan;
    }

    @RequestMapping(name= "/delete", method = RequestMethod.DELETE)
    public void Del(@RequestParam("mada")String MaDA){
        Duan duan = duanRepository.findOne(MaDA);
        duanRepository.delete(duan);
    }
}

