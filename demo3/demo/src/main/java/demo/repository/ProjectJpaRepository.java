package demo.repository;

import demo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
public interface ProjectJpaRepository extends JpaRepository<Project,String> {
}
