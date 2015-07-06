package demo.Return;

import demo.model.CompanyEmployee;

import java.util.List;
import java.util.Objects;

/**
 * Created by Nguyen Hoang on 06-Jul-15.
 */
public class BeanList {
    public List<CompanyEmployee> companyEmployeeList;

    public BeanList(List<CompanyEmployee> companyEmployeeList){
        this.companyEmployeeList = companyEmployeeList;
    }
}
