package demo.controller;

import demo.Return.NhanvienReturn;
import demo.model.Congty;
import demo.model.Duan;
import demo.model.Nhanvien;
import demo.model.User;
import demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;


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
    public NhanvienReturn ADD(@RequestParam("name")String name,
                              @RequestParam("passwordnv")String pwnv,
                              HttpSession session){
        HashMap errorlist = new HashMap();
        NhanvienReturn nhanvienReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Nhanvien nhanvien = new Nhanvien(name, pwnv);
            nhanvienJpaRepository.save(nhanvien);
            errorlist.put(0, "Successed");
            nhanvienReturn = new NhanvienReturn(errorlist);
            return nhanvienReturn;
        }else{
            errorlist.put(-1,"Access Denied");
            nhanvienReturn = new NhanvienReturn(errorlist);
            return nhanvienReturn;
        }
    }

    @RequestMapping(value ="/nvlogin",method= RequestMethod.POST)
    public NhanvienReturn NVlogin(@RequestParam("manv")String manv,
                         @RequestParam("password")String password,
                         HttpSession session) {
        HashMap errorlist = new HashMap();
        NhanvienReturn nhanvienReturn;
        Nhanvien nhanvien = nhanvienJpaRepository.findOne(manv);
        if(nhanvien!=null) {
            if (BCrypt.checkpw(password, nhanvien.getPassword())) {
                session.setAttribute("nhanvien", nhanvien);
                errorlist.put(0,"Access Granted");
                nhanvienReturn = new NhanvienReturn(errorlist);
                return nhanvienReturn;
            } else {
                errorlist.put(-1,"Password is not correct");
                nhanvienReturn = new NhanvienReturn(errorlist);
                return nhanvienReturn;
            }
        }else{
            errorlist.put(-2,"ID is not correct");
            nhanvienReturn = new NhanvienReturn(errorlist);
            return nhanvienReturn;
        }
    }
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public NhanvienReturn Edit(@RequestParam("name") String name,
                               HttpSession session) {
        HashMap errorlist = new HashMap();
        NhanvienReturn nhanvienReturn;
        Nhanvien nhanvien = (Nhanvien) session.getAttribute("nhanvien2");

        if (nhanvien != null) {
            nhanvien.setName(name);
            nhanvienJpaRepository.save(nhanvien);
            errorlist.put(0,"Successful");
            nhanvienReturn = new NhanvienReturn(nhanvien,errorlist);
            return nhanvienReturn;
        } else {
            errorlist.put(-1, "Access Denied");
            nhanvienReturn = new NhanvienReturn(errorlist);
            return nhanvienReturn;
        }
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public NhanvienReturn ChangePass(@RequestParam("newpassword") String newpass,
                                     @RequestParam("oldpassword")String oldpass,
                                     HttpSession session){
        HashMap errorlist = new HashMap();
        NhanvienReturn nhanvienReturn;
        Nhanvien nhanvien = (Nhanvien) session.getAttribute("nhanvien");
        if(nhanvien!=null){
            if(BCrypt.checkpw(oldpass,nhanvien.getPassword())){
                nhanvien.setPassword(BCrypt.hashpw(newpass,BCrypt.gensalt()));
                nhanvienJpaRepository.save(nhanvien);
                errorlist.put(1,"Password is changed");
                nhanvienReturn = new NhanvienReturn(nhanvien,errorlist);
                return nhanvienReturn;
            }else{
                errorlist.put(-2,"Password is not correct");
                nhanvienReturn = new NhanvienReturn(errorlist);
                return nhanvienReturn;
            }
        }else{
            errorlist.put(-1,"Access Denied");
            nhanvienReturn = new NhanvienReturn(errorlist);
            return nhanvienReturn;
        }
    }


}
