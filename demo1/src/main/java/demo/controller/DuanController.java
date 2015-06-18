package demo.controller;

import demo.model.Duan;
import demo.repository.DuanRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Table;

/**
 * Created by Nguyen Hoang on 17-Jun-15.
 */
@RestController
@Table(name = "/duan")
class DuanController {
    private DuanRepository duanRepository;

    @Autowired
    public DuanController (DuanRepository duanRepository){
        this.duanRepository = duanRepository;
    }


    @RequestMapping(value="/update",method = RequestMethod.GET)
    public Duan putDuan(
            @RequestParam("MaDA") String MaDA,
            @RequestParam("tenDA") String TenDA,
            @RequestParam("macty") String macty,
            @RequestParam("MaNV") String MaNVQL) {
        Duan duan = new Duan();

        duan.setMaDA(MaDA);
        duan.setTenDA(TenDA);
        duan.setMacty(macty);
        duan.setMaNV(MaNVQL);
        duanRepository.save(duan);
        return duan;
    }


    @RequestMapping(value="/edit",method = RequestMethod.GET)
    public Duan editDuan(
            @RequestParam("MaDA") String MaDA,
            @RequestParam("tenDA") String TenDA,
            @RequestParam("macty") String macty,
            @RequestParam("MaNV") String MaNVQL) {
        Duan duan = duanRepository.findOne(MaDA);
        duan.setTenDA(TenDA);
        duan.setMacty(macty);
        duan.setMaNV(MaNVQL);
        duanRepository.save(duan);
        return duan;
    }

    @RequestMapping(value = ("/delete"),method = RequestMethod.GET)
    private void DelDA(@RequestParam("MaDA")String MaDA){
        Duan duan = duanRepository.findOne(MaDA);
        duanRepository.delete(duan);
    }
}
