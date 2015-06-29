package demo.repository;

import demo.model.Nhanvien;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.AssertFalse;
import java.util.List;

/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
public interface NhanvienRepository extends CrudRepository<Nhanvien,String> {
    @Modifying
    @Transactional
    @Query("delete from Nhanvien nv where nv.macty=?1")
    void DelNV(String macty);

    @RestResource(exported = false)
    @Query("select nhanvien from Nhanvien nhanvien where nhanvien.macty=?1")
    List<Nhanvien> listnhanvienCT(String macty);

    @RestResource(exported = false)
    @Query("select nhanvien from Nhanvien nhanvien where nhanvien.MaDa=?1")
    List<Nhanvien> listnhanvienDA(String macty);

}
