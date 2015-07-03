package demo.repository;

import demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nguyen Hoang on 02-Jul-15.
 */
public interface TaskJpaRepository extends JpaRepository<Task,String> {
    List<Task> findByProjectid(String projectid);

    List<Task> findByParentid(String parentid);

    List<Task> deleteByParentid(String parentid);

    List<Task> deleteByProjectid(String projectid);
}
