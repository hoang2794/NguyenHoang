package demo.Return;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BeanBasic {
    private String TypeBean;
    public String message;
    public Integer resultcode;
    protected BeanBasic(){}

    public BeanBasic(Integer resultcode,String typeBean){
        this.message= (String)hashmap.get(resultcode);
        this.resultcode=resultcode;
        this.TypeBean= typeBean;
    }

    public String getTypeBean() {
        return TypeBean;
    }

    public void setTypeBean(String typeBean) {
        TypeBean = typeBean;
    }

    Map<Integer,String> hashmap = new HashMap<Integer,String>() {
        {
            put(0, "SUCCESSFUL");
            put(-1, "ACCESS DENIED");
            put(-2, "COMPANY_DOES_NOT_EXISTS");
            put(-3, "EMPLOYEE_DOES_NOT_EXISTS");
            put(-4, "PROJECT_DOES_NOT_EXISTS");
            put(-20, "YOU DON'T OWN THIS COMPANY");
            put(-50, "ID IS NOT CORRECT");
            put(-51, "PASSWORD IS NOT CORRECT");
            put(-100, "ADD FAIL");
            put(-200, "PROJECT AND EMPLOYEE EXISTS");
        }
    };

    public void setHashmap(HashMap<Integer, String> hashmap) {
        this.hashmap = hashmap;
    }

    public Integer getResultcode() {
        return resultcode;
    }

    public void setResultcode(Integer resultcode) {
        this.resultcode = resultcode;
    }
}
