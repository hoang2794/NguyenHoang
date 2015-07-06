package demo.model;

import javax.persistence.*;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table (name="person")
public class Person extends RootTime{
    private String name;
    private String password;

    protected Person(){
    }

    public Person(String name,String password){
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
