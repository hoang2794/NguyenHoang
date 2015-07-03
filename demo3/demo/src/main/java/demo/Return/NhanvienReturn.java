package demo.Return;

import demo.model.Nhanvien;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
public class NhanvienReturn extends  ErrorList{
    private Nhanvien nhanvien;

    private List<Nhanvien> nhanvienList;

    protected NhanvienReturn(){}

    public NhanvienReturn(Nhanvien nhanvien,HashMap errorlist){
        super(errorlist);
        this.nhanvien=nhanvien;
    }

    public NhanvienReturn(List<Nhanvien> nhanvienList,HashMap errorlist){
        super(errorlist);
        this.nhanvienList=nhanvienList;
    }

    public NhanvienReturn(HashMap errorlist){
        super(errorlist);
        this.nhanvien=null;
        this.nhanvienList=null;
    }

    public Nhanvien getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(Nhanvien nhanvien) {
        this.nhanvien = nhanvien;
    }

    public List<Nhanvien> getNhanvienList() {
        return nhanvienList;
    }

    public void setNhanvienList(List<Nhanvien> nhanvienList) {
        this.nhanvienList = nhanvienList;
    }
}
