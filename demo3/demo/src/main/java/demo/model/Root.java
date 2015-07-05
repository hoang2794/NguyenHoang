package demo.model;

import javax.persistence.*;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Root {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "generatorid")
    @TableGenerator(table="Number",name = "generatorid")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
