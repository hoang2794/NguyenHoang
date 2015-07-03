package demo.Return;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.HashMap;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ErrorList {
    private HashMap<Integer,String> errorlist;

    protected ErrorList(){}

    public ErrorList(HashMap errorlist){
        this.errorlist=errorlist;
    }

    public HashMap<Integer, String> getErrorlist() {
        return errorlist;
    }

    public void setErrorlist(HashMap<Integer, String> errorlist) {
        this.errorlist = errorlist;
    }
}
