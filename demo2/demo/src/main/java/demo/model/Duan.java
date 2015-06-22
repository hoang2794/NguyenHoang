package demo.model;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.soap.Node;
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
    private String MaNV;
    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,targetEntity = Task.class,fetch = FetchType.LAZY)
    private List<Task> listTask;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,targetEntity = Nhanvien.class,fetch = FetchType.LAZY)
    private List<Nhanvien> listNV;

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


    public String getMaNV() {return MaNV;}
    public void setMaNV(String maNV) {this.MaNV = maNV;}

}


