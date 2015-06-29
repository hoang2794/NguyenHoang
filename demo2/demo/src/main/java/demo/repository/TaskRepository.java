package demo.repository;

import demo.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
public interface TaskRepository extends CrudRepository<Task,Integer> {

    @Modifying
    @Transactional
    @Query("delete from Task taskchild where taskchild.task_parent_id=?1")
    void DelChild(Integer id);

    @Modifying
    @Transactional
    @Query("delete from Task task where task.MaDA=?1")
    void DelTask(String MaDA);

    @RestResource(exported = false)
    @Query("select taskchild from Task taskchild where taskchild.task_parent_id=?1")
    List<Task> listoftaskchild(Integer id);

    @RestResource(exported = false)
    @Query("select task from Task task where task.MaDA=?1")
    List<Task> listoftask(String mada);


}
