package demo.controller;

import demo.Return.CompanyProjectorBean;
import demo.Return.ResultCode;
import demo.model.Company;
import demo.model.CompanyProjector;
import demo.model.User;
import demo.repository.CompanyJpaRepository;
import demo.repository.CompanyProjectorRepository;
import demo.repository.ProjectJpaRepository;
import demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */

@RestController
@RequestMapping("/companyprojector")
public class CompanyProjectorController {
    @Autowired
    CompanyController companyController;

    @Autowired
    ProjectJpaRepository projectJpaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyProjectorRepository companyProjectorRepository;

    @Autowired
    CompanyJpaRepository companyJpaRepository;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public CompanyProjectorBean Add(@RequestParam("companyid")String companyid,
                                   @RequestParam("projectid")String projectid,
                                   HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findOne(companyid);
            if (user.getId() == company.getBossid()){
                CompanyProjector companyProjector = new CompanyProjector();
                companyProjector.setCompanyid(companyid);
                companyProjector.setProjectid(projectid);
                return new CompanyProjectorBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new CompanyProjectorBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
            }
        }else{
            return new CompanyProjectorBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CompanyProjectorBean Delete(@RequestParam("companyid")String companyid,
                                      @RequestParam("projectid")String projectid,
                                      HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findOne(companyid);
            if (user.getId() == company.getBossid()) {
                CompanyProjector companyProjector = companyProjectorRepository.findByCompanyidAndProjectid(companyid,projectid);
                companyProjectorRepository.delete(companyProjector);
                return new CompanyProjectorBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new CompanyProjectorBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
            }
        }else{
            return new CompanyProjectorBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value = "/deletebycompanyid", method = RequestMethod.POST)
    public CompanyProjectorBean Deletebycompanyid(@RequestParam("companyid")String companyid,
                                                 HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findOne(companyid);
            if (user.getId() == company.getBossid()) {
                companyProjectorRepository.deleteByCompanyid(companyid);
                return new CompanyProjectorBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new CompanyProjectorBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
            }
        }else{
            return new CompanyProjectorBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }
}
