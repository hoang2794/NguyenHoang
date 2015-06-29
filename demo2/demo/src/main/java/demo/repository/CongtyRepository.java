package demo.repository;

import demo.model.Congty;
import demo.model.Nhanvien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
public interface CongtyRepository extends CrudRepository<Congty,String> {
    @RestResource(exported = false)
    @Query("select congty from Congty congty where congty.bossid=?1")
    List<Congty> listofcongty(Integer bossid);
}
