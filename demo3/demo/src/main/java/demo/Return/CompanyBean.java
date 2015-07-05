package demo.Return;

import demo.model.Company;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
public class CompanyBean extends BeanBasic {
    private Company company;

    List<Company> companyList;

    protected CompanyBean(){}

    public CompanyBean(Company company, Integer resultcode){
        super(resultcode,"CompanyBean");
        this.company = company;
    }

    public CompanyBean(List<Company> companyList, Integer resultcode){
        super(resultcode,"CompanyBean");
        this.companyList = companyList;
    }

    public CompanyBean(Integer resultcode){
        super(resultcode,"CompanyBean");
        this.company =null;
        this.companyList =null;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }
}
