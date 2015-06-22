package demo.repository;

import org.springframework.data.repository.CrudRepository;
import demo.model.Duan;
/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
public interface DuanRepository extends CrudRepository<Duan,String> {
}
