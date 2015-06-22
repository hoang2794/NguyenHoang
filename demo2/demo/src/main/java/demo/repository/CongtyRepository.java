package demo.repository;

import demo.model.Congty;
import demo.model.Nhanvien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.*;
/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
public interface CongtyRepository extends CrudRepository<Congty,String> {
}
