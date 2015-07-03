package demo.repository;

import demo.model.CongtyNhanvien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */
public interface CongtyNhanvienRepository extends JpaRepository<CongtyNhanvien,String> {
    CongtyNhanvien findByCompanyidAndEmployeeid(String companyid,String employeeid);

    List<CongtyNhanvien> deleteByCompanyid(String companyid);

    List<CongtyNhanvien> deleteByEmployeeid(String employeeid);

    List<CongtyNhanvien> findByCompanyid(String companyid);
}
