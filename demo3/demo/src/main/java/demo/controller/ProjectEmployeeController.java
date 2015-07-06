package demo.controller;

import demo.Return.ProjectEmployeeBean;
import demo.Return.ResultCode;
import demo.model.ProjectEmployee;
import demo.model.User;
import demo.repository.CompanyJpaRepository;
import demo.repository.EmployeeJpaRepository;
import demo.repository.ProjectorEmployeeRepository;
import demo.repository.ProjectJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by Nguyen Hoang on 05-Jul-15.
 */
public class ProjectEmployeeController {

    @Autowired
    ProjectJpaRepository projectJpaRepository;

    @Autowired
    EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    ProjectorEmployeeRepository projectorEmployeeRepository;

    @Autowired
    CompanyJpaRepository companyJpaRepository;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ProjectEmployeeBean Add(@RequestParam("employeeid")Long employeeid,
                                    @RequestParam("projectid")String projectid,
                                     @RequestParam("manager")Integer manager,
                                    HttpSession session) {
        if(Check.Check(projectid)) {
            User user = (User) session.getAttribute("abc");
            if (user != null) {
                ProjectEmployee check = projectorEmployeeRepository.findByProjectidAndEmployeeid(projectid, employeeid);
                if (check != null) {
                    return new ProjectEmployeeBean(ResultCode.RESULT_CODE_ADD_FAIL);
                } else {
                    ProjectEmployee projectEmployee = new ProjectEmployee();
                    projectEmployee.setEmployeeid(employeeid);
                    projectEmployee.setProjectid(projectid);
                    projectEmployee.setManager(manager);
                    projectorEmployeeRepository.save(projectEmployee);
                    return new ProjectEmployeeBean(ResultCode.RESULT_CODE_SUCCESSFUL);
                }
            } else {
                return new ProjectEmployeeBean(ResultCode.RESULT_CODE_ACCESSDENIED);
            }
        }else{
            return new ProjectEmployeeBean(ResultCode.RESULT_CODE_ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ProjectEmployeeBean Delete(@RequestParam("employeeid")Long employeeid,
                                       @RequestParam("projectid")String projectid,
                                       HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
                ProjectEmployee projectEmployee = projectorEmployeeRepository.findByProjectidAndEmployeeid(projectid,employeeid);
                if(projectEmployee!=null) {
                    projectorEmployeeRepository.delete(projectEmployee);
                    return new ProjectEmployeeBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new ProjectEmployeeBean(ResultCode.RESULT_CODE_PROJECT_AND_EMPLOYEE_DO_NOT_EXISTS);
            }
        }else{
            return new ProjectEmployeeBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value = "/deletebyprojectid", method = RequestMethod.POST)
    public ProjectEmployeeBean Deletebycompanyid(@RequestParam("companyid")String projectid,
                                                  HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
                projectorEmployeeRepository.deleteByProjectid(projectid);
                return new ProjectEmployeeBean(ResultCode.RESULT_CODE_SUCCESSFUL);
        }else{
            return new ProjectEmployeeBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }
}
