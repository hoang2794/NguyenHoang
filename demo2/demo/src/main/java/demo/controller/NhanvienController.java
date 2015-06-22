package demo.controller;

import demo.model.Congty;
import demo.model.Nhanvien;
import demo.repository.CongtyRepository;
import demo.repository.NhanvienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Table;
import java.util.*;

/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@RestController
@Table(name="/nhanvien")
public class NhanvienController {
    @Autowired
    NhanvienRepository nhanvienRepository;

    @Autowired
    CongtyRepository congtyRepository;
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Nhanvien ADD(@RequestParam("macty")String macty,
                         @RequestParam("MaNV")String MaNV,
                         @RequestParam("name")String ten){
        Nhanvien nhanvien = new Nhanvien();


        nhanvien.setMaNV(MaNV);
        nhanvien.setTen(ten);
        nhanvien.setMacty(macty);
        nhanvienRepository.save(nhanvien);
        return nhanvien;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    private Nhanvien Edit(@RequestParam("MaNV")String MaNV,
                          @RequestParam("name")String ten,
                          @RequestParam("macty")String macty){
        Nhanvien nhanvien = nhanvienRepository.findOne(MaNV);

        nhanvien.setTen(ten);
        nhanvien.setMacty(macty);
        nhanvienRepository.save(nhanvien);
        return nhanvien;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    private void Del(@RequestParam("MaNV")String MaNV){
        Nhanvien nhanvien = nhanvienRepository.findOne(MaNV);

        nhanvienRepository.delete(nhanvien.getMacty());
    }

    private void List(@RequestParam("MaNV")String MaNV,
                      @RequestParam("macty")String macty){
        for(int i=0;i<10;i++){
            Nhanvien nhanvien = new Nhanvien();
            nhanvien.setMaNV(MaNV);
            nhanvienRepository.save(nhanvien);
            for(int j=0; j<15;j++){
                Nhanvien congty = new Nhanvien();
                congty.setMacty(macty);
                congty.setCongty(nhanvien);
                nhanvienRepository.save(congty);
            }
        }
    }
}
