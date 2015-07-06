package demo.repository;

import demo.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nguyen Hoang on 02-Jul-15.
 */
public interface CompanyJpaRepository extends JpaRepository<Company,Long> {
    List<Company> findByBossid(Long bossid);

    Company findByCompanyid(String companyid);
}
