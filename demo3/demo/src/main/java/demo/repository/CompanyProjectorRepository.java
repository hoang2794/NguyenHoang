package demo.repository;

import demo.model.CompanyProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */
public interface CompanyProjectorRepository extends JpaRepository<CompanyProject,Long> {
    CompanyProject findByCompanyidAndProjectid(String companyid,String projectid);

    List<CompanyProject> findByCompanyid(String companyid);

    List<CompanyProject> deleteByCompanyid(String companyid);
}
