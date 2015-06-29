package demo.controller;

import demo.model.Congty;
import demo.model.Duan;
import demo.model.Nhanvien;
import demo.model.User;
import demo.repository.CongtyRepository;
import demo.repository.DuanRepository;
import demo.repository.NhanvienRepository;
import demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    UserRepository userRepository;
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Nhanvien ADD(@RequestParam("manv")String MaNV,
                        @RequestParam("name")String ten,
                        @RequestParam("macty")String macty,
                        @RequestParam("mada")String mada,
                        @RequestParam("bossid")Integer id,
                        @RequestParam("password")String password){
        Nhanvien nhanvien = new Nhanvien();
        if(userRepository.Login(id,password)) {
            if (nhanvienRepository.exists(MaNV)) {
                return nhanvien;
            } else if (congtyRepository.exists(macty)) {
                        if (duanRepository.exists(mada)) {
                            nhanvien.setMaNV(MaNV);
                            nhanvien.setTen(ten);
                        return nhanvien;
                        }
                    }
            }
        return nhanvien;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    private Nhanvien Edit(@RequestParam("manv")String MaNV,
                          @RequestParam("name")String ten,
                          @RequestParam("macty")String macty,
                          @RequestParam("mada")String mada,
                          @RequestParam("bossid")Integer id,
                          @RequestParam("password")String password){
        Nhanvien nhanvien = new Nhanvien();
        if(userRepository.Login(id,password)==true){
            nhanvien = nhanvienRepository.findOne(MaNV);
            if (congtyRepository.exists(macty)) {
                if (duanRepository.exists(mada)) {
                    nhanvien.setTen(ten);
                    nhanvien.setMacty(macty);
                    nhanvien.setMaDa(mada);
                    return nhanvien;
                }
            }
        }
        return nhanvien;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    private String Del(@RequestParam("id")Integer id,
                     @RequestParam("password")String password,
                     @RequestParam("MaNV")String MaNV){
        if(userRepository.Login(id,password)==true) {
            Nhanvien nhanvien = nhanvienRepository.findOne(MaNV);
            nhanvienRepository.delete(nhanvien);
            return "OK";
        }
        return "ERROR";
    }


}
