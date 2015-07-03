package demo.controller;

import demo.model.Congty;
import demo.model.Duan;
import demo.model.Nhanvien;
import demo.model.User;
import demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@RestController
@RequestMapping("/nhanvien")
public class NhanvienController {
    @Autowired
    NhanvienJpaRepository nhanvienJpaRepository;

    @Autowired
    CongtyJpaRepository congtyJpaRepository;

    @Autowired
    DuanJpaRepository duanJpaRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Nhanvien ADD(@RequestParam("manv")String manv,
                        @RequestParam("name")String name,
                        @RequestParam("macty")String macty,
                        @RequestParam("passwordnv")String pwnv,
                        @RequestParam("projectid")String projectid,
                        HttpSession session){
        User user = (User) session.getAttribute("abc");
        if(user!=null){
            if (congtyJpaRepository.exists(macty)){
                if(user.getId()== congtyJpaRepository.findOne(macty).getBossid()) {
                    String salt = BCrypt.gensalt();
                    Nhanvien nhanvien = new Nhanvien(manv,name,BCrypt.hashpw(pwnv,salt),salt);
                    nhanvien.setMacty(macty);
                    if (duanJpaRepository.exists(projectid)) {
                        nhanvien.setProjectid(projectid);
                        Duan duan = duanJpaRepository.findOne(projectid);    //Tìm dự án theo mã rồi thêm mã manager vào
                        duan.setManager(nhanvien.getId());
                        duanJpaRepository.save(duan);
                    }
                    nhanvienJpaRepository.save(nhanvien);
                    return nhanvien;
                }
            }
        }
        return null;
    }

    @RequestMapping(value ="/nvlogin",method= RequestMethod.POST)
    public Integer NVlogin(@RequestParam("manv")String manv,
                         @RequestParam("password")String password,
                         HttpSession session) {
        Nhanvien nhanvien = nhanvienJpaRepository.findOne(manv);
        if(nhanvien!=null) {
            if (BCrypt.checkpw(password, nhanvien.getPassword())) {
                session.setAttribute("nhanvien2", nhanvien);
                return 0;
            } else {
                return -1;
            }
        }
        return null;
    }
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public Nhanvien Edit(@RequestParam("name") String name,
                         @RequestParam("newpassword") String newpass,
                         @RequestParam("oldpassword")String oldpass,
                         @RequestParam("projectid") String projectid,
                         HttpSession session){
        Nhanvien nhanvien = (Nhanvien) session.getAttribute("nhanvien2");
        User user = (User) session.getAttribute("abc");
        if(nhanvien!=null) {
            nhanvien.setName(name);
            Duan duan = duanJpaRepository.findOne(projectid);
            if (duan != null) {
                if (user != null) {
                    if(nhanvien.getMacty().compareTo(duan.getMacty())==0)
                    nhanvien.setProjectid(projectid);
                    nhanvienJpaRepository.save(nhanvien);
                    return nhanvien;
                }
            }
            return nhanvien;
        }
        return null;
    }

    @RequestMapping(value = "/invite",method = RequestMethod.GET)
    public Nhanvien Invite(@RequestParam("mada")String mada,
                           @RequestParam("nhanvien")String manv1,
                           HttpSession session){
        Nhanvien nhanvien1 = nhanvienJpaRepository.findOne(manv1);
        Nhanvien managerda = (Nhanvien) session.getAttribute("nhanvien2");
        Duan duan = duanJpaRepository.findOne(mada);
        if(managerda!=null){
            if(duan!=null) {
                if(nhanvien1!=null) {
                    if (managerda.getId().compareTo(duan.getManager()) == 0) {
                        nhanvien1.setProjectid(mada);
                        nhanvienJpaRepository.save(nhanvien1);
                        return nhanvien1;
                    }
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Integer Del(@RequestParam("manv") String MaNV,
                       HttpSession session){
        User user = (User) session.getAttribute("abc");
        if(user!=null){
            Nhanvien nhanvien = nhanvienJpaRepository.findOne(MaNV);
            if(nhanvien!=null) {
                nhanvien.setMacty("0");
                nhanvienJpaRepository.save(nhanvien);
                return 0;
            }
        }
        return -1;
    }


}
