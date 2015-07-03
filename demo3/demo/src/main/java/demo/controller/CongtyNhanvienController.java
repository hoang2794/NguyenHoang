package demo.controller;

import demo.Return.CongtyNhanvienReturn;
import demo.model.Congty;
import demo.model.CongtyNhanvien;
import demo.model.User;
import demo.repository.CongtyJpaRepository;
import demo.repository.CongtyNhanvienRepository;
import demo.repository.NhanvienJpaRepository;
import demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */
public class CongtyNhanvienController {
    @Autowired
    CongtyNhanvienRepository congtyNhanvienRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CongtyJpaRepository congtyJpaRepository;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public CongtyNhanvienReturn Add(@RequestParam("companyid")String companyid,
                                    @RequestParam("employeeid")String employeeid,
                                    HttpSession session) {
        HashMap errorlist = new HashMap();
        CongtyNhanvienReturn congtyNhanvienReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            CongtyNhanvien congtyNhanvien = congtyNhanvienRepository.findOne(companyid);
            if (user.getId() == congtyNhanvien.getBossid()){
                congtyNhanvien.setEmployeeid(employeeid);
                congtyNhanvienRepository.save(congtyNhanvien);
                errorlist.put(0, "Successful");
                congtyNhanvienReturn = new CongtyNhanvienReturn(errorlist);
                return congtyNhanvienReturn;
            }else{
                errorlist.put(-2,"You don't own this company");
                congtyNhanvienReturn = new CongtyNhanvienReturn(errorlist);
                return congtyNhanvienReturn;
            }
        }else{
            errorlist.put(-1,"Access Denied");
            congtyNhanvienReturn = new CongtyNhanvienReturn(errorlist);
            return congtyNhanvienReturn;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CongtyNhanvienReturn Delete(@RequestParam("companyid")String companyid,
                                       @RequestParam("employeeid")String employeeid,
                                       HttpSession session) {
        HashMap errorlist = new HashMap();
        CongtyNhanvienReturn congtyNhanvienReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Congty congty = congtyJpaRepository.findOne(companyid);
            if (user.getId() == congty.getBossid()) {
                CongtyNhanvien congtyNhanvien = congtyNhanvienRepository.findByCompanyidAndEmployeeid(companyid, employeeid);
                congtyNhanvienRepository.delete(congtyNhanvien);
                errorlist.put(0, "Successful");
                congtyNhanvienReturn = new CongtyNhanvienReturn(errorlist);
                return congtyNhanvienReturn;
            } else {
                errorlist.put(-2, "You don't own this company");
                congtyNhanvienReturn = new CongtyNhanvienReturn(errorlist);
                return congtyNhanvienReturn;
            }
        } else {
            errorlist.put(-1, "Access Denied");
            congtyNhanvienReturn = new CongtyNhanvienReturn(errorlist);
            return congtyNhanvienReturn;
        }
    }

    @RequestMapping(value = "/deletebycompanyid", method = RequestMethod.POST)
    public CongtyNhanvienReturn Delete(@RequestParam("companyid")String companyid,
                                       HttpSession session) {
        HashMap errorlist = new HashMap();
        CongtyNhanvienReturn congtyNhanvienReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Congty congty = congtyJpaRepository.findOne(companyid);
            if (user.getId() == congty.getBossid()) {
                congtyNhanvienRepository.deleteByCompanyid(companyid);
                errorlist.put(0, "Successful");
                congtyNhanvienReturn = new CongtyNhanvienReturn(errorlist);
                return congtyNhanvienReturn;
            } else {
                errorlist.put(-2, "You don't own this company");
                congtyNhanvienReturn = new CongtyNhanvienReturn(errorlist);
                return congtyNhanvienReturn;
            }
        } else {
            errorlist.put(-1, "Access Denied");
            congtyNhanvienReturn = new CongtyNhanvienReturn(errorlist);
            return congtyNhanvienReturn;
        }
    }
}
