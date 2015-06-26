package demo.repository;

import demo.model.Task;
import org.springframework.data.repository.CrudRepository;



/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
public interface TaskRepository extends CrudRepository<Task,Integer> {
}
