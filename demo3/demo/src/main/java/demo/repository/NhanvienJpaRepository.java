package demo.repository;

import demo.model.Nhanvien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nguyen Hoang on 02-Jul-15.
 */
public interface NhanvienJpaRepository extends JpaRepository<Nhanvien,String> {
    List<Nhanvien> findByMacty(String macty);

    List<Nhanvien> findByProjectid(String projectid);

    Long deleteByMacty(String macty);
}
