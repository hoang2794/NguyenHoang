package demo.controller;

import demo.model.User;
import demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Quan Do on 6/17/2015.
 */
@RestController
@RequestMapping("/boss")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
}
