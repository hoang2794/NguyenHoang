package demo.repository;

import demo.model.Congty;
import org.springframework.data.repository.CrudRepository;
/**
 * Created by Nguyen Hoang on 17-Jun-15.
 */
public interface CongtyRepository extends CrudRepository<Congty,String> {
}