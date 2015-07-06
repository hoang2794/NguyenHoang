package demo.repository;

import demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nguyen Hoang on 02-Jul-15.
 */
public interface EmployeeJpaRepository extends JpaRepository<Employee,Long> {
//    List<Employee> findByCom(String macty);
//
//    List<Employee> findByProjectid(String projectid);
//
//    Long deleteByMacty(String macty);
}
