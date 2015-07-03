package demo.Return;

import demo.model.CongtyNhanvien;

import java.util.HashMap;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */
public class CongtyNhanvienReturn extends ErrorList{
    private CongtyNhanvien congtyNhanvien ;

    public CongtyNhanvienReturn(HashMap errorlist){
        super(errorlist);
    }

    public CongtyNhanvien getCongtyNhanvien() {
        return congtyNhanvien;
    }

    public void setCongtyNhanvien(CongtyNhanvien congtyNhanvien) {
        this.congtyNhanvien = congtyNhanvien;
    }
}
