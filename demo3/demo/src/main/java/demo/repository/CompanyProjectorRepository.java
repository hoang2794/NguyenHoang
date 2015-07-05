package demo.repository;

import demo.model.CompanyProjector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */
public interface CompanyProjectorRepository extends JpaRepository<CompanyProjector,Long> {
    CompanyProjector findByCompanyidAndProjectid(String companyid,String projectid);

    List<CompanyProjector> findByCompanyid(String companyid);

    List<CompanyProjector> deleteByCompanyid(String companyid);
}
