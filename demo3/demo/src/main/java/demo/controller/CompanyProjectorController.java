package demo.controller;

import demo.Return.CompanyProjectBean;
import demo.Return.ResultCode;
import demo.model.Company;
import demo.model.CompanyProject;
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
import java.util.List;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */

@RestController
@RequestMapping("/companyproject")
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
    public CompanyProjectBean Add(@RequestParam("companyid")String companyid,
                                   @RequestParam("projectid")String projectid,
                                   HttpSession session) {
        if(Check.Check(companyid) && Check.Check(projectid)) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findByCompanyid(companyid);
            if (user.getId() == company.getBossid()) {
                CompanyProject companyProject = new CompanyProject();
                companyProject.setCompanyid(companyid);
                companyProject.setProjectid(projectid);
                companyProjectorRepository.save(companyProject);
                return new CompanyProjectBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            } else {
                return new CompanyProjectBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
            }
        } else {
            return new CompanyProjectBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }else {
        return new CompanyProjectBean(ResultCode.RESULT_CODE_ERROR);
    }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CompanyProjectBean Delete(@RequestParam("companyid")String companyid,
                                      @RequestParam("projectid")String projectid,
                                      HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findByCompanyid(companyid);
            if (user.getId() == company.getBossid()) {
                CompanyProject companyProject = companyProjectorRepository.findByCompanyidAndProjectid(companyid,projectid);
                companyProjectorRepository.delete(companyProject);
                return new CompanyProjectBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new CompanyProjectBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
            }
        }else{
            return new CompanyProjectBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value = "/deletebycompanyid", method = RequestMethod.POST)
    public CompanyProjectBean Deletebycompanyid(@RequestParam("companyid")String companyid,
                                                 HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findByCompanyid(companyid);
            if (user.getId() == company.getBossid()) {
                companyProjectorRepository.deleteByCompanyid(companyid);
                return new CompanyProjectBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new CompanyProjectBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
            }
        }else{
            return new CompanyProjectBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value = "/listbycompanyid",method = RequestMethod.GET)
    public CompanyProjectBean ListByCompanyid(@RequestParam("companyid")String companyid,
                                              HttpSession session){
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findByCompanyid(companyid);
            if (user.getId() == company.getBossid()) {
                List<CompanyProject> listbycompanyid = companyProjectorRepository.findByCompanyid(companyid);
                return new CompanyProjectBean(listbycompanyid,ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new CompanyProjectBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
            }
        }else{
            return new CompanyProjectBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }
}
