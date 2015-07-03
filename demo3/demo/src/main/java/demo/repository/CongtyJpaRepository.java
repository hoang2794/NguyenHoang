package demo.repository;

import demo.model.Congty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nguyen Hoang on 02-Jul-15.
 */
public interface CongtyJpaRepository extends JpaRepository<Congty,String> {
    List<Congty> findByBossid(String bossid);
}
