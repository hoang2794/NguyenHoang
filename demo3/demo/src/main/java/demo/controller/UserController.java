package demo.controller;

import demo.Return.ResultCode;
import demo.model.Company;
import demo.model.User;
import demo.Return.UserBean;
import demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Quan Do on 6/17/2015.
 */
@RestController
@RequestMapping("/boss")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyController companyController;

    @Autowired
    CompanyJpaRepository companyJpaRepository;

    @Autowired
    EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    ProjectJpaRepository projectJpaRepository;

    @Autowired
    CompanyEmployeeRepository companyEmployeeRepository;

    @Autowired
    CompanyProjectorRepository companyProjectorRepository;
    @RequestMapping(value ="/get",method= RequestMethod.GET)
    public User getUser(@RequestParam("id")Long id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(value ="/login",method= RequestMethod.POST)
    public UserBean Login(@RequestParam("id")Long id,
                        @RequestParam("password")String password,
                         HttpSession session) {
        User user = userRepository.findOne(id);
        if (BCrypt.checkpw(password,user.getPassword())) {
            session.setAttribute("abc",user);
            return new UserBean(user, ResultCode.RESULT_CODE_SUCCESSFUL);
        }else{
            return new UserBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public UserBean putUser(@RequestParam("name") String name,
                              @RequestParam("password") String password) {
        User user = new User(name,BCrypt.hashpw(password,BCrypt.gensalt()));
        userRepository.save(user);
        return new UserBean(user,ResultCode.RESULT_CODE_SUCCESSFUL);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public UserBean deleteUser(@RequestParam("id") Long id,
                           HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            List<Company> companyList = companyJpaRepository.findByBossid(id);
            int count = companyList.size();
            for (int i = 0; i < count; i++) {
                companyController.Del(companyList.get(i).getMacty(),session);
            }
            userRepository.delete(user);
            return new UserBean(user, ResultCode.RESULT_CODE_SUCCESSFUL);
        }else{
            return new UserBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public UserBean editUser(@RequestParam("name") String name,
                         HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            user.setName(name);
            userRepository.save(user);
            return new UserBean(user, ResultCode.RESULT_CODE_SUCCESSFUL);
        }else{
            return new UserBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public UserBean ChangePass(@RequestParam("newpassword") String newpassword,
                                 @RequestParam("oldpassword")String oldpassword,
                                 HttpSession session){
            User user = (User) session.getAttribute("abc");
            if (user != null) {
                if (BCrypt.checkpw(oldpassword, user.getPassword())) {
                    user.setPassword(BCrypt.hashpw(newpassword, BCrypt.gensalt()));
                    return new UserBean(ResultCode.RESULT_CODE_PASSWORD_CHANGED);
                } else {
                    return new UserBean(ResultCode.RESULT_CODE_PASSWORD_IS_NOT_CORRECT);
                }
            }else{
                return new UserBean(ResultCode.RESULT_CODE_ACCESSDENIED);
            }
    }

    //List Employee, list Project có trong công ty mà chủ sở hữu
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public UserBean List(@RequestParam("id")Long id,
                     HttpSession session) {
        User user = (User) session.getAttribute("abc");
        if(user!=null) {
            if(user.getId().compareTo(id)==0) {
                user.setListcongty(companyJpaRepository.findByBossid(id));
                List<Company> companyList = companyJpaRepository.findByBossid(id);
                for (Company companyE : companyList) {
                    companyE.setListNV(companyEmployeeRepository.findByCompanyid(companyE.getMacty()));
                    companyE.setListDA(companyProjectorRepository.findByCompanyid(companyE.getMacty()));
                    companyJpaRepository.save(companyE);
                }
                userRepository.save(user);
                return new UserBean(user,ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new UserBean(ResultCode.RESULT_CODE_DONT_OWN_COMPANY);
            }
        }else{
            return new UserBean(ResultCode.RESULT_CODE_ACCESSDENIED);

        }
    }

    @RequestMapping(value = "/listboss",method = RequestMethod.POST)
    public UserBean Listboss(HttpSession session){
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            List<User> userList = (List<User>) userRepository.findAll();
            return new UserBean(userList,ResultCode.RESULT_CODE_SUCCESSFUL);
        }else{
            return new UserBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }
}
