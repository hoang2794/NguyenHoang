package demo.repository;

import demo.model.Duan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nguyen Hoang on 02-Jul-15.
 */
public interface DuanJpaRepository extends JpaRepository<Duan,String> {
    List<Duan> findByMacty(String macty);
}
