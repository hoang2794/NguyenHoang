package demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Nguyen Hoang on 19-Jun-15.
 */
@Entity
public class Employee extends Person {
    private Employee(){}
    public Employee(String name, String password){
        super(name,password);
    }

}

