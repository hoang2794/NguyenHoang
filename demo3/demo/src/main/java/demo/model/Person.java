package demo.model;

import javax.persistence.*;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {
    @Id
    private String id;
    private String name;
    private String password;
    private String salt;

    protected Person(){
    }

    public Person(String id,String name,String password,String salt){
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
