package demo.controller;

import demo.model.Congty;
import demo.model.User;
import demo.Return.UserReturn;
import demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
    CongtyController congtyController;

    @Autowired
    CongtyJpaRepository congtyJpaRepository;

    @Autowired
    NhanvienJpaRepository nhanvienJpaRepository;

    @Autowired
    DuanJpaRepository duanJpaRepository;

    @Autowired
    CongtyNhanvienRepository congtyNhanvienRepository;
    @RequestMapping(value ="/get",method= RequestMethod.GET)
    public User getUser(@RequestParam("id")String id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(value ="/login",method= RequestMethod.POST)
    public UserReturn Login(@RequestParam("id")String id,
                        @RequestParam("password")String password,
                         HttpSession session) {
        HashMap errorlist = new HashMap();
        UserReturn userReturn;
        User user = userRepository.findOne(id);
        if (BCrypt.checkpw(password,user.getPassword())) {
            session.setAttribute("abc",user);
            errorlist.put(0,"Successful");
            userReturn = new UserReturn(user,errorlist);
            return userReturn;
        }else{
            errorlist.put(-1,"Access Denied");
            userReturn = new UserReturn(errorlist);
            return userReturn;
        }
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public UserReturn putUser(@RequestParam("name") String name,
                              @RequestParam("password") String password) {
        HashMap errorList = new HashMap();
        UserReturn userReturn;
        User user = new User(name,BCrypt.hashpw(password,BCrypt.gensalt()));
        userRepository.save(user);
        errorList.put(1,"Successful");
        userReturn= new UserReturn(user,errorList);
        return userReturn;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public UserReturn deleteUser(@RequestParam("id") String id,
                           HttpSession session) {
        HashMap errorList = new HashMap();
        UserReturn userReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            List<Congty> congtyList = congtyJpaRepository.findByBossid(id);
            int count = congtyList.size();
            for (int i = 0; i < count; i++) {
                congtyController.Del(congtyList.get(i).getMacty(),session);
            }
            userRepository.delete(user);
            errorList.put(2,"Successful");
            userReturn = new UserReturn(errorList);
            return userReturn;
        }else{
            errorList.put(0,"Access Denied");
            userReturn = new UserReturn(errorList);
            return userReturn;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public UserReturn editUser(@RequestParam("name") String name,
                         HttpSession session) {
        HashMap errorList = new HashMap();
        UserReturn userReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            user.setName(name);
            userRepository.save(user);
            errorList.put(0,"Successful");
            userReturn = new UserReturn(errorList);
            return userReturn;
        }else{
            errorList.put(-1,"Access Denied");
            userReturn = new UserReturn(errorList);
            return userReturn;
        }
    }

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public UserReturn ChangePass(@RequestParam("newpassword") String newpassword,
                                 @RequestParam("oldpassword")String oldpassword,
                                 HttpSession session){
            HashMap errorList = new HashMap();
            UserReturn userReturn;
            User user = (User) session.getAttribute("abc");
            if (user != null) {
                if (BCrypt.checkpw(oldpassword, user.getPassword())) {
                    user.setPassword(BCrypt.hashpw(newpassword, BCrypt.gensalt()));
                    errorList.put(0, "Password is changed");
                    userReturn = new UserReturn(errorList);
                    return userReturn;
                } else {
                    errorList.put(-1, "Password is not correct");
                    userReturn = new UserReturn(errorList);
                    return userReturn;
                }
            }else{
                errorList.put(-2,"Access Denied");
                userReturn = new UserReturn(errorList);
                return userReturn;
            }
    }

    //List Nhanvien, list Duan có trong công ty mà chủ sở hữu
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public UserReturn List(@RequestParam("id")String id,
                     HttpSession session) {
        HashMap errorList = new HashMap();
        UserReturn userReturn;
        User user = (User) session.getAttribute("abc");
        if(user!=null) {
            if(user.getId().compareTo(id)==0) {
                user.setListcongty(congtyJpaRepository.findByBossid(id));
                List<Congty> congtyList = congtyJpaRepository.findByBossid(id);
                for (Congty congtyE : congtyList) {
                    congtyE.setListNV(congtyNhanvienRepository.findByCompanyid(congtyE.getMacty()));
                    congtyE.setListDA(duanJpaRepository.findByMacty(congtyE.getMacty()));
                    congtyJpaRepository.save(congtyE);
                }
                userRepository.save(user);
                errorList.put(3, "OK");
                userReturn = new UserReturn(user, errorList);
                return userReturn;
            }else{
                errorList.put(-5, "You don't own this company");
                userReturn = new UserReturn(errorList);
                return userReturn;
            }
        }else{
            errorList.put(-4,"ERROR");
            userReturn = new UserReturn(errorList);
            return userReturn;

        }
    }

    @RequestMapping(value = "/listboss",method = RequestMethod.POST)
    public UserReturn Listboss(HttpSession session){
        HashMap errorList = new HashMap();
        UserReturn userReturn;
        User user = (User) session.getAttribute("abc");
        if (user != null) {
            List<User> userList = (List<User>) userRepository.findAll();
            errorList.put(3,"OK");
            userReturn = new UserReturn(userList,errorList);
            return userReturn;
        }else{
            errorList.put(-4,"ERROR");
            userReturn = new UserReturn(errorList);
            return userReturn;
        }
    }
}
