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
import java.lang.*;

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
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Nhanvien ADD(@RequestParam("manv")String MaNV,
                        @RequestParam("name")String ten,
                        @RequestParam("macty")String macty,
                        @RequestParam("passwordnv")String pwnv,
                        @RequestParam("mada")String mada,
                        @RequestParam("bossid")Integer id,
                        @RequestParam("password")String password){
        Nhanvien nhanvien = new Nhanvien();
        User user = userRepository.Login(id,password);
        if(user!=null) {
            if (congtyRepository.exists(macty)){
                    nhanvien.setMaNV(MaNV);
                    nhanvien.setTen(ten);
                    nhanvien.setMacty(macty);
                    nhanvien.setPassword(pwnv);
                        if(duanRepository.exists(mada)) {
                            nhanvien.setMaDa(mada);
                        }
                    nhanvienRepository.save(nhanvien);
                return nhanvien;
            }
        }
        return nhanvien;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    private Nhanvien Edit(@RequestParam("manv")String MaNV,
                          @RequestParam("name")String ten,
                          @RequestParam("newpassword")String newpass,
                          @RequestParam("mada")String mada,
                          @RequestParam("password")String password){
        Nhanvien nhanvien = nhanvienRepository.Login(MaNV,password);
        if(nhanvien!=null){
            nhanvien = nhanvienRepository.findOne(MaNV);
            nhanvien.setTen(ten);
            nhanvien.setMaDa(mada);
            nhanvien.setPassword(newpass);
            nhanvienRepository.save(nhanvien);
            return nhanvien;
        }
        return null;
    }

    @RequestMapping(value = "/invite",method = RequestMethod.GET)
    public Nhanvien Invite(@RequestParam("manager")String manv,
                           @RequestParam("password")String password,
                           @RequestParam("mada")String mada,
                           @RequestParam("nhanvien")String manv1){
        Nhanvien nhanvien1 = nhanvienRepository.findOne(manv1);
        Nhanvien nhanvien = nhanvienRepository.Login(manv,password);
        Duan duan = duanRepository.findOne(mada);
        if(nhanvien!=null){
            if(nhanvien.getMaNV().compareTo(duan.getManager())==0){
                nhanvien1.setMaDa(mada);
                nhanvienRepository.save(nhanvien1);
                return nhanvien1;
            }
        }
        return nhanvien;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    private String Del(@RequestParam("bossid")Integer id,
                     @RequestParam("password")String password,
                     @RequestParam("manv")String MaNV){
        User user = userRepository.Login(id,password);
        if(user!=null) {
            Nhanvien nhanvien = nhanvienRepository.findOne(MaNV);
            nhanvienRepository.delete(nhanvien);
            return "OK";
        }
        return "ERROR";
    }


}
