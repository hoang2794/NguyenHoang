package demo.controller;

import demo.Return.CompanyEmployeeBean;
import demo.Return.CompanyProjectorBean;
import demo.Return.ResultCode;
import demo.model.Company;
import demo.model.CompanyEmployee;
import demo.model.User;
import demo.repository.CompanyJpaRepository;
import demo.repository.CompanyEmployeeRepository;
import demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */
@RestController
@RequestMapping("/companyemployee")
public class CompanyEmployeeController {
    @Autowired
    CompanyEmployeeRepository companyEmployeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyJpaRepository companyJpaRepository;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public CompanyEmployeeBean Add(@RequestParam("companyid")String companyid,
                                    @RequestParam("employeeid")String employeeid,
                                    HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findOne(companyid);
            if (user.getId() == company.getBossid()){
                CompanyEmployee companyEmployee = new CompanyEmployee();
                companyEmployee.setCompanyid(companyid);
                companyEmployee.setEmployeeid(employeeid);
                companyEmployeeRepository.save(companyEmployee);
                return new CompanyEmployeeBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new CompanyEmployeeBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
            }
        }else{
            return new CompanyEmployeeBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CompanyEmployeeBean Delete(@RequestParam("companyid")String companyid,
                                       @RequestParam("employeeid")String employeeid,
                                       HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findOne(companyid);
            if (user.getId() == company.getBossid()) {
                CompanyEmployee companyEmployee = companyEmployeeRepository.findByCompanyidAndEmployeeid(companyid, employeeid);
                companyEmployeeRepository.delete(companyEmployee);
                return new CompanyEmployeeBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new CompanyEmployeeBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
            }
        }else{
            return new CompanyEmployeeBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value = "/deletebycompanyid", method = RequestMethod.POST)
    public CompanyEmployeeBean Deletebycompanyid(@RequestParam("companyid")String companyid,
                                       HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findOne(companyid);
            if (user.getId() == company.getBossid()) {
                companyEmployeeRepository.deleteByCompanyid(companyid);
                return new CompanyEmployeeBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new CompanyEmployeeBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
            }
        }else{
            return new CompanyEmployeeBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }
}
