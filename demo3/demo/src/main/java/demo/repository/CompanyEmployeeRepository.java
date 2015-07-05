package demo.repository;

import demo.model.CompanyEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */
public interface CompanyEmployeeRepository extends JpaRepository<CompanyEmployee,Long> {
    CompanyEmployee findByCompanyidAndEmployeeid(String companyid,String employeeid);

    List<CompanyEmployee> deleteByCompanyid(String companyid);

    List<CompanyEmployee> deleteByEmployeeid(String employeeid);

    List<CompanyEmployee> findByCompanyid(String companyid);
}
