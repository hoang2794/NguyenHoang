package demo.model;

import javax.persistence.*;

/**
 * Created by Nguyen Hoang on 19-Jun-15.
 */
@Entity
@Table(name="NV")
public class Nhanvien {
    @Id
    private String MaNV;
    private String ten;
    private String macty;
    private String MaDa;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMaDa() {
        return MaDa;
    }

    public void setMaDa(String maDa) {
        MaDa = maDa;
    }

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
        this.MaNV = maNV;
    }


    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }

}

