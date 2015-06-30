package demo.repository;

import demo.model.Congty;
import demo.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by Quan Do on 6/17/2015.
 */
@RestResource(path= "boss")
public interface UserRepository  extends CrudRepository<User,Integer>{
    @RestResource(exported = false)
    @Query("select user from User user where user.id=?1 and user.password=?2")
    User Login(Integer id,String password);
}
