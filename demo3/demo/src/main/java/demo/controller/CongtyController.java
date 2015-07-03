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
    @Autowired
    CongtyNhanvienRepository congtyNhanvienRepository;

    @Autowired
    CongtyNhanvienController congtyNhanvienController;
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

    @RequestMapping(value="/editcompanyid", method = RequestMethod.PUT)
    public CongtyReturn Edit(@RequestParam("companyid")String companyid,
                             @RequestParam("newcompanyid")String newcompanyid,
                             @RequestParam("name")String name,
                             HttpSession session) {
        HashMap errorlist = new HashMap();
        CongtyReturn congtyReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Congty congty = congtyJpaRepository.findOne(companyid);
                if (congty != null) {
                    if (user.getId().compareTo(congty.getBossid()) == 0) {
                        congty.setName(name);
                        List<Duan> duanList = duanJpaRepository.findByMacty(companyid);
                        if(duanList!=null) {
                            int count = duanList.size();
                            for (int i = 0; i <= count; i++) {
                                Duan duan = duanJpaRepository.findOne(duanList.get(i).getProjectid());
                                duan.setMacty(newcompanyid);
                                duanJpaRepository.save(duan);
                            }
                        }
                        List<CongtyNhanvien> congtyNhanvienList = congtyNhanvienRepository.findByCompanyid(companyid);
                        if(congtyNhanvienList!=null) {
                            int count1 = congtyNhanvienList.size();
                            for (int j = 0; j < count1; j++) {
                                CongtyNhanvien congtyNhanvien = congtyNhanvienRepository.findByCompanyidAndEmployeeid(companyid,congtyNhanvienList.get(j).getEmployeeid());
                                congtyNhanvien.setMacty(newcompanyid);
                                congtyNhanvienRepository.save(congtyNhanvien);
                            }
                        }
                        congty.setMacty(newcompanyid);
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

    @RequestMapping(value="/editname", method = RequestMethod.PUT)
    public CongtyReturn Editname(@RequestParam("companyid")String companyid,
                                 @RequestParam("name")String name,
                                 HttpSession session) {
        HashMap errorlist = new HashMap();
        CongtyReturn congtyReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Congty congty = congtyJpaRepository.findOne(companyid);
            if (congty != null) {
                if (user.getId().compareTo(congty.getBossid()) == 0) {
                    congty.setName(name);
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
    public CongtyReturn Del(@RequestParam("companyid")String companyid,HttpSession session) {
        HashMap errorlist = new HashMap();
        CongtyReturn congtyReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Congty congty = congtyJpaRepository.findOne(companyid);
            if (congty != null) {
                if (user.getId().compareTo(congty.getBossid()) == 0) {
                    congtyNhanvienController.Delete(companyid,session);
                    List<Duan> duanList = duanJpaRepository.findByMacty(companyid);
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
    public CongtyReturn ListNV(@RequestParam("companyid")String companyid,
                         HttpSession session) {
        HashMap errorlist = new HashMap();
        CongtyReturn congtyReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Congty congty = congtyJpaRepository.findOne(companyid);
            if (congty != null) {
                if (user.getId().compareTo(congty.getBossid()) == 0) {
                    congty.setListNV(congtyNhanvienRepository.findByCompanyid(companyid));
                    congty.setListDA(duanJpaRepository.findByMacty(companyid));
                    List<Duan> duanList = duanJpaRepository.findByMacty(companyid);
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
