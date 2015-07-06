package demo.Return;

import demo.model.CompanyEmployee;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */
public class CompanyEmployeeBean extends BeanBasic {
    public Bean bean;
    public BeanList beanlist;

    public CompanyEmployeeBean(Integer resultcode){
        super(resultcode,"CompanyEmployeeBean");
    }

    public CompanyEmployeeBean(List<CompanyEmployee> listbycompanyid,Integer resultcode){
        super(resultcode,"CompanyEmployeeBean");
        this.beanlist = new BeanList(listbycompanyid);
    }
    public CompanyEmployeeBean(CompanyEmployee companyEmployee,Integer resultcode){
        super(resultcode,"CompanyEmployeeBean");
        this.bean = new Bean(companyEmployee);
    }
}