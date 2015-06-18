package demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Nguyen Hoang on 18-Jun-15.
 */
@Entity
@Table (name="phancong")
public class Phancong {
    private String MaDA;
    private String MaNV;
    private System thoigiantao;

    public String getMaDA() {
        return MaDA;
    }

    public void setMaDA(String maDA) {
        MaDA = maDA;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }
}
