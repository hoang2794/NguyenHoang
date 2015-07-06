package demo.Return;

import demo.model.CompanyEmployee;
import demo.model.CompanyProject;

import java.util.List;


/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */
public class CompanyProjectBean extends BeanBasic {
    private CompanyProject companyProject;
    public List<CompanyProject> listbycompanyid;
    public Bean bean;
    public CompanyProjectBean(Integer resultcode){
        super(resultcode,"CompanyProjectBean");
    }
    public CompanyProjectBean(List<CompanyProject> listbycompanyid,Integer resultcode){
        super(resultcode,"CompanyProjectBean");
        this.listbycompanyid = listbycompanyid;
    }
    public CompanyProjectBean(CompanyProject companyProject, Integer resultcode){
        super(resultcode,"CompanyProjectBean");
        this.bean = new Bean(companyProject);
    }
    public CompanyProject getCompanyProject() {
        return companyProject;
    }

    public void setCompanyProject(CompanyProject companyProject) {
        this.companyProject = companyProject;
    }
}
