package demo.controller;

import demo.Return.CompanyBean;
import demo.Return.ResultCode;
import demo.model.*;
import demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyJpaRepository companyJpaRepository;


    @Autowired
    ProjectController projectController;

    @Autowired
    ProjectJpaRepository projectJpaRepository;

    @Autowired
    EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    TaskJpaRepository taskJpaRepository;
    @Autowired
    CompanyEmployeeRepository companyEmployeeRepository;

    @Autowired
    CompanyEmployeeController companyEmployeeController;

    @Autowired
    CompanyProjectorRepository companyProjectorRepository;

    @Autowired
    CompanyProjectorController companyProjectorController;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public CompanyBean ADD(@RequestParam("companyid")String companyid,
                      @RequestParam("name")String name,
                      HttpSession session) {
        Company company = new Company();
        if(Check.Check(companyid)){
        User user = (User) session.getAttribute("abc");
        if (user != null) {                                // check login
            if (companyJpaRepository.findByCompanyid(companyid) != null) {
                return new CompanyBean(ResultCode.RESULT_CODE_ADD_FAIL);
            } else {
                company.setCompanyid(companyid);
                company.setName(name);
                company.setBossid(user.getId());
                companyJpaRepository.save(company);
                return new CompanyBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }
        }else{
            return new CompanyBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
        } else {
            return new CompanyBean(ResultCode.RESULT_CODE_ERROR);
        }
    }

    @RequestMapping(value="/editcompanyid", method = RequestMethod.POST)
    public CompanyBean Edit(@RequestParam("companyid")String companyid,
                             @RequestParam("newcompanyid")String newcompanyid,
                             @RequestParam("name")String name,
                             HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findByCompanyid(companyid);
                if (company != null) {
                    if (user.getId() == company.getBossid()) {
                        company.setName(name);
                        List<CompanyProject> companyProjectList = companyProjectorRepository.findByCompanyid(companyid);
                        if(companyProjectList !=null) {
                            int count = companyProjectList.size();
                            for (int i = 0; i <= count; i++) {
                                CompanyProject companyProject = companyProjectorRepository.findOne(companyProjectList.get(i).getId());
                                companyProject.setCompanyid(newcompanyid);
                                companyProjectorRepository.save(companyProject);
                            }
                        }
                        List<CompanyEmployee> companyEmployeeList = companyEmployeeRepository.findByCompanyid(companyid);
                        if(companyEmployeeList !=null) {
                            int count1 = companyEmployeeList.size();
                            for (int j = 0; j < count1; j++) {
                                CompanyEmployee companyEmployee = companyEmployeeRepository.findByCompanyidAndEmployeeid(companyid, companyEmployeeList.get(j).getEmployeeid());
                                companyEmployee.setCompanyid(newcompanyid);
                                companyEmployeeRepository.save(companyEmployee);
                            }
                        }
                        company.setCompanyid(newcompanyid);
                        companyJpaRepository.save(company);
                        return new CompanyBean(company,ResultCode.RESULT_CODE_SUCCESSFUL);
                    } else {
                        return new CompanyBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
                    }
                }else{
                    return new CompanyBean(ResultCode.RESULT_CODE_COMPANY_DOES_NOT_EXISTS);
                }
        }else{
            return new CompanyBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value="/editname", method = RequestMethod.POST)
    public CompanyBean Editname(@RequestParam("companyid")String companyid,
                                 @RequestParam("name")String name,
                                 HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findByCompanyid(companyid);
            if (company != null) {
                if (user.getId() == company.getBossid()) {
                    company.setName(name);
                    companyJpaRepository.save(company);
                    return new CompanyBean(company, ResultCode.RESULT_CODE_SUCCESSFUL);
                } else {
                    return new CompanyBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
                }
            }else{
                return new CompanyBean(ResultCode.RESULT_CODE_COMPANY_DOES_NOT_EXISTS);
            }
        }else{
            return new CompanyBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }


    @RequestMapping(value= "/delete",method = RequestMethod.POST)
    public CompanyBean Del(@RequestParam("companyid")String companyid,HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findByCompanyid(companyid);
            if (company != null) {
                if (user.getId()== company.getBossid()) {
                    companyEmployeeController.Deletebycompanyid(companyid, session);
                    companyProjectorController.Deletebycompanyid(companyid,session);
                    companyJpaRepository.delete(company);
                    return new CompanyBean(ResultCode.RESULT_CODE_SUCCESSFUL);
                }else {
                    return new CompanyBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
                }
            } else {
                return new CompanyBean(ResultCode.RESULT_CODE_COMPANY_DOES_NOT_EXISTS);
            }
        } else {
            return new CompanyBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CompanyBean ListNV(@RequestParam("companyid")String companyid,
                         HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Company company = companyJpaRepository.findByCompanyid(companyid);
            if (company != null) {
                if (user.getId().compareTo(company.getBossid()) == 0) {
                    company.setListNV(companyEmployeeRepository.findByCompanyid(companyid));
                    company.setListDA(companyProjectorRepository.findByCompanyid(companyid));
                    companyJpaRepository.save(company);
                    return new CompanyBean(company,ResultCode.RESULT_CODE_SUCCESSFUL);
                } else {
                    return new CompanyBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
                }
            } else {
                return new CompanyBean(ResultCode.RESULT_CODE_COMPANY_DOES_NOT_EXISTS);
            }
        } else {
            return new CompanyBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

}
