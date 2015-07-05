package demo.Return;

import demo.model.CompanyEmployee;

import java.util.HashMap;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */
public class CompanyEmployeeBean extends BeanBasic {
    private CompanyEmployee companyEmployee;

    public CompanyEmployeeBean(Integer resultcode){
        super(resultcode,"CompanyEmployeeBean");
    }

    public CompanyEmployee getCompanyEmployee() {
        return companyEmployee;
    }

    public void setCompanyEmployee(CompanyEmployee companyEmployee) {
        this.companyEmployee = companyEmployee;
    }
}
