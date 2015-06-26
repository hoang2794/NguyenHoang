package demo.controller;

import demo.model.Congty;
import demo.model.Duan;
import demo.model.Nhanvien;
import demo.repository.CongtyRepository;
import demo.repository.DuanRepository;
import demo.repository.NhanvienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.persistence.Table;
import java.util.*;

/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@RestController
@RequestMapping("/nhanvien")
public class NhanvienController {
    @Autowired
    NhanvienRepository nhanvienRepository;

    @Autowired
    CongtyRepository congtyRepository;

    @Autowired
    DuanRepository duanRepository;
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Nhanvien ADD(@RequestParam("manv")String MaNV,
                        @RequestParam("name")String ten,
                        @RequestParam("macty")String macty,
                        @RequestParam("mada")String mada){
        Congty congty = congtyRepository.findOne(macty);
        congty.getMacty();
        Duan duan = duanRepository.findOne(mada);
        duan.getMaDA();
        Nhanvien nhanvien = new Nhanvien();

        nhanvien.setMaNV(MaNV);
        nhanvien.setTen(ten);
        nhanvien.setNhanvien(congty);
        nhanvien.setDuan(duan);
        nhanvienRepository.save(nhanvien);
        return nhanvien;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    private Nhanvien Edit(@RequestParam("manv")String MaNV,
                          @RequestParam("name")String ten,
                          @RequestParam("macty")String macty,
                          @RequestParam("mada")String mada){
        Nhanvien nhanvien = nhanvienRepository.findOne(MaNV);
        Congty congty = congtyRepository.findOne(macty);
        congty.getMacty();
        Duan duan = duanRepository.findOne(mada);
        duan.getMaDA();
        nhanvien.setTen(ten);
        nhanvien.setDuan(duan);
        nhanvien.setNhanvien(congty);
        nhanvienRepository.save(nhanvien);
        return nhanvien;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    private void Del(@RequestParam("MaNV")String MaNV){
        Nhanvien nhanvien = nhanvienRepository.findOne(MaNV);
        nhanvienRepository.delete(nhanvien);
    }


}
