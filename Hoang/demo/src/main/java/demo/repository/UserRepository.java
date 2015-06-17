package demo.repository;

import demo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Quan Do on 6/17/2015.
 */
public interface UserRepository  extends CrudRepository<User,Integer>{

}
