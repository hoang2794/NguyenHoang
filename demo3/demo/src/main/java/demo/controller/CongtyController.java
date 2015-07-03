package demo.controller;

import demo.Return.CongtyReturn;
import demo.model.*;
import demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
    CongtyJpaRepository congtyJpaRepository;


    @Autowired
    DuanController duanController;

    @Autowired
    DuanJpaRepository duanJpaRepository;

    @Autowired
    NhanvienJpaRepository nhanvienJpaRepository;

    @Autowired
    TaskJpaRepository taskJpaRepository;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public CongtyReturn ADD(@RequestParam("macty")String macty,
                      @RequestParam("name")String name,
                      HttpSession session){
        HashMap errorlist = new HashMap();
        CongtyReturn congtyReturn;
        Congty congty = new Congty();
        User user = (User) session.getAttribute("abc");
        if(user!=null) {                                // check login
            if (congtyJpaRepository.exists(macty)) {
                errorlist.put(-2, "Add Fail");
                congtyReturn = new CongtyReturn(errorlist);
                return congtyReturn;
            } else {
                congty.setMacty(macty);
                congty.setName(name);
                congty.setBossid(user.getId());
                congtyJpaRepository.save(congty);
                errorlist.put(0, "Successful");
                congtyReturn = new CongtyReturn(congty,errorlist);
                return congtyReturn;
            }
        }
        errorlist.put(-1, "Access Denied");
        congtyReturn = new CongtyReturn(errorlist);
        return congtyReturn;
    }

    @RequestMapping(value="/edit", method = RequestMethod.PUT)
    public CongtyReturn Edit(@RequestParam("macty")String macty,
                             @RequestParam("newmacty")String newmacty,
                             @RequestParam("name")String name,
                             HttpSession session) {
        HashMap errorlist = new HashMap();
        CongtyReturn congtyReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Congty congty = congtyJpaRepository.findOne(macty);
                if (congty != null) {
                    if (user.getId().compareTo(congty.getBossid()) == 0) {
                        congty.setName(name);
                        List<Duan> duanList = duanJpaRepository.findByMacty(macty);
                        if(duanList!=null) {
                            int count = duanList.size();
                            for (int i = 0; i <= count; i++) {
                                Duan duan = duanJpaRepository.findOne(duanList.get(i).getProjectid());
                                duan.setMacty(newmacty);
                                duanJpaRepository.save(duan);
                            }
                        }
                        List<Nhanvien> nhanvienList = nhanvienJpaRepository.findByMacty(macty);
                        if(nhanvienList!=null) {
                            int count1 = nhanvienList.size();
                            for (int j = 0; j < count1; j++) {
                                Nhanvien nhanvien = nhanvienJpaRepository.findOne(nhanvienList.get(j).getId());
                                nhanvien.setMacty(newmacty);
                                nhanvienJpaRepository.save(nhanvien);
                            }
                        }
                        congty.setMacty(newmacty);
                        congtyJpaRepository.save(congty);
                        errorlist.put(0, "Successfull");
                        congtyReturn = new CongtyReturn(congty, errorlist);
                        return congtyReturn;
                    } else {
                        errorlist.put(-3, "You don't own this company");
                        congtyReturn = new CongtyReturn(errorlist);
                        return congtyReturn;
                    }
                }else{
                    errorlist.put(-2,"Company is not exists");
                    congtyReturn= new CongtyReturn(errorlist);
                    return congtyReturn;
                }
        }else{
            errorlist.put(-1,"Access Denied");
            congtyReturn= new CongtyReturn(errorlist);
            return congtyReturn;
        }
    }

    @RequestMapping(value= "/delete",method = RequestMethod.DELETE)
    public CongtyReturn Del(@RequestParam("macty")String macty,HttpSession session) {
        HashMap errorlist = new HashMap();
        CongtyReturn congtyReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Congty congty = congtyJpaRepository.findOne(macty);
            if (congty != null) {
                if (user.getId().compareTo(congty.getBossid()) == 0) {
                    nhanvienJpaRepository.deleteByMacty(macty);
                    List<Duan> duanList = duanJpaRepository.findByMacty(macty);
                    if (duanList != null) {
                        int count = duanList.size();
                        for (int i = 0; i < count; i++) {
                            duanController.Del(duanList.get(i).getProjectid(), session);
                        }
                    }
                    congtyJpaRepository.delete(congty);
                    errorlist.put(0, "Successfull");
                    congtyReturn = new CongtyReturn(congty, errorlist);
                    return congtyReturn;
                }else {
                    errorlist.put(-3, "You don't own this company");
                    congtyReturn = new CongtyReturn(errorlist);
                    return congtyReturn;
                }
            } else {
                errorlist.put(-2, "Company is not exists");
                congtyReturn = new CongtyReturn(errorlist);
                return congtyReturn;
            }
        } else {
            errorlist.put(-1, "Access Denied");
            congtyReturn = new CongtyReturn(errorlist);
            return congtyReturn;
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CongtyReturn ListNV(@RequestParam("macty")String macty,
                         HttpSession session) {
        HashMap errorlist = new HashMap();
        CongtyReturn congtyReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Congty congty = congtyJpaRepository.findOne(macty);
            if (congty != null) {
                if (user.getId().compareTo(congty.getBossid()) == 0) {
                    congty.setListNV(nhanvienJpaRepository.findByMacty(macty));
                    congty.setListDA(duanJpaRepository.findByMacty(macty));
                    List<Duan> duanList = duanJpaRepository.findByMacty(macty);
                    if (duanList != null) {
                        for (Duan duanE : duanList) {
                            duanJpaRepository.save(duanController.List(duanE.getProjectid()));
                        }
                        congtyJpaRepository.save(congty);

                    }
                    errorlist.put(0, "Successfull");
                    congtyReturn = new CongtyReturn(congty, errorlist);
                    return congtyReturn;
                } else {
                    errorlist.put(-3, "You don't own this company");
                    congtyReturn = new CongtyReturn(errorlist);
                    return congtyReturn;
                }
            } else {
                errorlist.put(-2, "Company is not exists");
                congtyReturn = new CongtyReturn(errorlist);
                return congtyReturn;
            }
        } else {
            errorlist.put(-1, "Access Denied");
            congtyReturn = new CongtyReturn(errorlist);
            return congtyReturn;
        }
    }

}
