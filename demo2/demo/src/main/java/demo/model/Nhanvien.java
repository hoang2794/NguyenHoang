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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="parent_task_id",referencedColumnName = "macty")
    private Nhanvien congty;

    public Nhanvien getCongty() {
        return congty;
    }

    public void setCongty(Nhanvien congty) {
        this.congty = congty;
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

