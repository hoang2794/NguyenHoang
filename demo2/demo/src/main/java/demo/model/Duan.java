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
    private String macty;
    private String manager;

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getMacty() {
        return macty;
    }

    public void setMacty(String macty) {
        this.macty = macty;
    }

    @Transient
    private List<Task> listTask;

    @Transient
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



}


