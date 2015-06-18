package demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Nguyen Hoang on 17-Jun-15.
 */
@Entity
@Table(name = "congty")
public class Congty {
    private String macty;
    private String name;
    private int boss;

    public String getMacty() {return macty;}

    public void setMacty(String macty) {this.macty = macty;}

    public String getName() {return name;}

    public void setName(String ten) {this.name = name;}

    public int getBoss() {return boss;}

    public void setBoss(int boss) {this.boss = boss;}
}
