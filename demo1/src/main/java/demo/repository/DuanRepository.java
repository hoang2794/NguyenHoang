package demo.repository;

import demo.model.Duan;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nguyen Hoang on 17-Jun-15.
 */
public interface DuanRepository extends CrudRepository<Duan,String> {
}
