package demo.repository;

import demo.model.Nhanvien;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nguyen Hoang on 17-Jun-15.
 */
public interface NhanvienRepository extends CrudRepository<Nhanvien,String> {
}
