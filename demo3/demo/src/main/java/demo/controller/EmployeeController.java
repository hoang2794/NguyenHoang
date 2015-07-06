package demo.controller;

import demo.Return.EmployeeBean;
import demo.Return.ResultCode;
import demo.model.Employee;
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
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    CompanyJpaRepository companyJpaRepository;

    @Autowired
    ProjectJpaRepository projectJpaRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public EmployeeBean ADD(@RequestParam("name")String name,
                              @RequestParam("passwordnv")String pwnv,
                              HttpSession session){
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            Employee employee = new Employee(name, BCrypt.hashpw(pwnv,BCrypt.gensalt()));
            employeeJpaRepository.save(employee);
            return new EmployeeBean(employee,ResultCode.RESULT_CODE_SUCCESSFUL);
        }else{
            return new EmployeeBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value ="/nvlogin",method= RequestMethod.POST)
    public EmployeeBean NVlogin(@RequestParam("employeeid")Long employeeid ,
                         @RequestParam("password")String password,
                         HttpSession session) {
        Employee employee = employeeJpaRepository.findOne(employeeid);
        if(employee !=null) {
            if (BCrypt.checkpw(password, employee.getPassword())) {
                session.setAttribute("employee", employee);
                return new EmployeeBean(employee,ResultCode.RESULT_CODE_SUCCESSFUL);
            } else {
                return new EmployeeBean(ResultCode.RESULT_CODE_PASSWORD_IS_NOT_CORRECT);
            }
        }else{
            return new EmployeeBean(ResultCode.RESULT_CODE_ID_IS_NOT_CORRECT);
        }
    }
    @RequestMapping(value = "/editname",method = RequestMethod.POST)
    public EmployeeBean Edit(@RequestParam("name") String name,
                               HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee != null) {
            employee.setName(name);
            employeeJpaRepository.save(employee);
            return new EmployeeBean(employee,ResultCode.RESULT_CODE_SUCCESSFUL);
        }else{
            return new EmployeeBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public EmployeeBean ChangePass(@RequestParam("newpassword") String newpass,
                                     @RequestParam("oldpassword")String oldpass,
                                     HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        if(employee !=null){
            if(BCrypt.checkpw(oldpass, employee.getPassword())){
                employee.setPassword(BCrypt.hashpw(newpass,BCrypt.gensalt()));
                employeeJpaRepository.save(employee);
                session.invalidate();
                return new EmployeeBean(ResultCode.RESULT_CODE_PASSWORD_CHANGED);
            }else{
                return new EmployeeBean(ResultCode.RESULT_CODE_PASSWORD_IS_NOT_CORRECT);
            }
        }else{
            return new EmployeeBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }


}
