package demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Nguyen Hoang on 17-Jun-15.
 */
@Entity
@Table(name="nhanvien")
public class Nhanvien {
    private String MaNV;
    private String ten;
    private String gioitinh;
    private String ngaysinh;

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


    public String getGioitinh() {
        return gioitinh;
    }
    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }


    public String getNgaysinh() {
        return ngaysinh;
    }
    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
}
