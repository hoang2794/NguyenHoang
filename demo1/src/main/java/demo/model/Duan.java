package demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Nguyen Hoang on 17-Jun-15.
 */
@Entity
@Table(name="duan")
public class Duan {
    private String MaDA;
    private String TenDA;
    private String macty;
    private String MaNVQL;

    public String getMaNV() {
        return MaNVQL;
    }

    public void setMaNV(String maNVQL) {
        this.MaNVQL = maNVQL;
    }

    public String getMaDA() {
        return MaDA;
    }

    public void setMaDA(String maDA) {
        this.MaDA = maDA;
    }

    public String getTenDA() {
        return TenDA;
    }

    public void setTenDA(String tenDA) {
        this.TenDA = tenDA;
    }

    public String getMacty() {
        return macty;
    }

    public void setMacty(String macty) {
        this.macty = macty;
    }

    /**
     * Created by Nguyen Hoang on 18-Jun-15.
     */
    public static class Task {
    }
}
