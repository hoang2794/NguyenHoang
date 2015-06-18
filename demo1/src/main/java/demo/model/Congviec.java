package demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Nguyen Hoang on 18-Jun-15.
 */
@Entity
@Table (name="congviec")
public class Congviec {

    private String macty;
    private String MaNV;

    public String getMacty() {
        return macty;
    }

    public void setMacty(String macty) {
        this.macty = macty;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }
}
