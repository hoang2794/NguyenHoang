package demo.model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@Entity
@Table(name = "duan")
public class Duan {
    @Id
    private String MaDA;
    private String TenDA;

        @Transient
        @OneToMany(mappedBy = "duan",cascade = CascadeType.ALL,targetEntity = Task.class,fetch = FetchType.LAZY)
        private List<Task> listTask;

        @Transient
        @OneToMany(mappedBy = "nhanvien",cascade = CascadeType.ALL,targetEntity = Nhanvien.class,fetch = FetchType.LAZY)
        private List<Nhanvien> listNV;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="macty",referencedColumnName = "macty")
        private Congty duan;

    public Congty getDuan() {
        return duan;
    }

    public void setDuan(Congty duan) {
        this.duan = duan;
    }

    public List<Nhanvien> getListNV() {
        return listNV;
    }

    public void setListNV(List<Nhanvien> listNV) {
        this.listNV = listNV;
    }


    public List<Task> getListTask() {
        return listTask;
    }

    public void setListTask(List<Task> listTask) {
        this.listTask = listTask;
    }

    public String getMaDA() {return MaDA;}
    public void setMaDA(String maDA) {this.MaDA = maDA;}


    public String getTenDA() {return TenDA;}
    public void setTenDA(String tenDA) {this.TenDA = tenDA;}



}


