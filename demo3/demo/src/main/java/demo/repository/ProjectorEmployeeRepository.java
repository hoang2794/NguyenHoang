package demo.repository;

import demo.model.ProjectEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nguyen Hoang on 05-Jul-15.
 */
public interface ProjectorEmployeeRepository extends JpaRepository<ProjectEmployee,String> {
    ProjectEmployee findByProjectidAndEmployeeid(String projectid,Long employeeid);

    List<ProjectEmployee> deleteByProjectid(String projectid);

    List<ProjectEmployee> findByProjectid(String projectid);
}
