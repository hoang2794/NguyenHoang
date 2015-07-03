package demo.Return;

import demo.model.Congty;
import demo.repository.CongtyJpaRepository;

import javax.persistence.Transient;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
public class CongtyReturn extends ErrorList {
    private Congty congty;

    List<Congty> congtyList;

    protected CongtyReturn(){}

    public CongtyReturn(Congty congty,HashMap errorlist){
        super(errorlist);
        this.congty=congty;
    }

    public CongtyReturn(List<Congty> congtyList,HashMap errorlist){
        super(errorlist);
        this.congtyList=congtyList;
    }

    public CongtyReturn(HashMap errorlist){
        super(errorlist);
        this.congty=null;
        this.congtyList=null;
    }

    public Congty getCongty() {
        return congty;
    }

    public void setCongty(Congty congty) {
        this.congty = congty;
    }

    public List<Congty> getCongtyList() {
        return congtyList;
    }

    public void setCongtyList(List<Congty> congtyList) {
        this.congtyList = congtyList;
    }
}
