package demo.controller;

import demo.model.Congty;
import demo.model.User;
import demo.repository.CongtyRepository;
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
    @RequestMapping(value ="/get",method= RequestMethod.GET)
    public User getUser(@RequestParam("id")Integer id) {
        User user = userRepository.findOne(id);
        return user;
    }

    @RequestMapping(value ="/login",method= RequestMethod.POST)
    public User getUser(@RequestParam("name")String name,
                        @RequestParam("password")String password) {

        User user = new User();
        return user;
    }

    @RequestMapping(value="/update",method = RequestMethod.GET)
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
    public void deleteUser(@RequestParam("id") Integer id) {
        User user = userRepository.findOne(id);
        userRepository.delete(user);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public User editUser(@RequestParam("id") Integer id,
                         @RequestParam("info") String info,
                         @RequestParam("name") String name,
                         @RequestParam("name") String password){
        User user = userRepository.findOne(id);
        user.setInfo(info);
        user.setName(name);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Congty> list(@RequestParam("id")Integer id){
        List<Congty> listcongty = userRepository.listofcongty(id);
        int count = listcongty.size();
        for(int i = 0;i<=count;i++){
            listcongty.get(i);
            return listcongty;
        }
        return listcongty;
    }

}
