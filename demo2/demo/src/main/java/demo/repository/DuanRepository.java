package demo.repository;

import demo.model.Nhanvien;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import demo.model.Duan;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
public interface DuanRepository extends CrudRepository<Duan,String> {
    @RestResource(exported = false)
    @Query("select duan from Duan duan where duan.macty=?1")
    List<Duan> listofDA(String macty);

    @Modifying
    @Transactional
    @Query("delete from Duan duan where duan.macty=?1")
    void DelDA(String macty);

}
