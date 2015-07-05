package demo.repository;

import demo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nguyen Hoang on 02-Jul-15.
 */
public interface ProjectJpaRepository extends JpaRepository<Project,String> {
    Project findByProjectid(String projectid);
}
