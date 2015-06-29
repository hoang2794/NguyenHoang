package demo.controller;

import demo.model.Congty;
import demo.model.Duan;
import demo.model.User;
import demo.repository.CongtyRepository;
import demo.repository.DuanRepository;
import demo.repository.NhanvienRepository;
import demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    CongtyRepository congtyRepository;

    @Autowired
    NhanvienRepository nhanvienRepository;

    @Autowired
    DuanRepository duanRepository;
    @RequestMapping(value ="/get",method= RequestMethod.GET)
    public User getUser(@RequestParam("id")Integer id) {
        User user = userRepository.findOne(id);
        return user;
    }

    @RequestMapping(value ="/login",method= RequestMethod.POST)
    public String getUser(@RequestParam("id")Integer id,
                        @RequestParam("password")String password) {
        User user = userRepository.findOne(id);
        if(user.getPassword()==password){
            return "OK";
        }
        return "ERROR";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public User putUser(@RequestParam("info") String info,
                        @RequestParam("name") String name,
                        @RequestParam("password") String password) {
        User user = new User();

        user.setInfo(info);
        user.setName(name);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam("id") Integer id,
                           @RequestParam("password")String password) {
        User user = userRepository.findOne(id);
        if(user.getPassword()== password) {
            userRepository.delete(user);
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public User editUser(@RequestParam("id") Integer id,
                         @RequestParam("info") String info,
                         @RequestParam("name") String name,
                         @RequestParam("name") String password){
        User user = userRepository.findOne(id);
        if(user.getPassword()==password){
            user.setInfo(info);
            user.setName(name);
            userRepository.save(user);
            return user;
        }
        return user;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public User List(@RequestParam("id")Integer id) {
        User user = userRepository.findOne(id);
        user.setListcongty(congtyRepository.listofcongty(id));
        List<Congty> congtyList = congtyRepository.listofcongty(id);
        for(Congty congtyE : congtyList){
            congtyE.setListNV(nhanvienRepository.listnhanvienCT(congtyE.getMacty()));
            congtyE.setListDA(duanRepository.listofDA(congtyE.getMacty()));
            congtyRepository.save(congtyE);
        }
        userRepository.save(user);
        return user;
    }
}
