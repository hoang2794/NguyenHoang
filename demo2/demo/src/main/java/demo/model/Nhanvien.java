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

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="macty",referencedColumnName = "macty")
        private Congty nhanvien;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="MaDA", referencedColumnName = "MaDA")
        private Duan duan;

        public Duan getDuan() {
            return duan;
        }

        public void setDuan(Duan duan) {
            this.duan = duan;
        }

    public Congty getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(Congty nhanvien) {
        this.nhanvien = nhanvien;
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

