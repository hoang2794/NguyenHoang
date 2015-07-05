package demo.Return;

import demo.model.CompanyProjector;

import java.util.HashMap;

/**
 * Created by Nguyen Hoang on 04-Jul-15.
 */
public class CompanyProjectorBean extends BeanBasic {
    private CompanyProjector companyProjector;
    public CompanyProjectorBean(Integer resultcode){
        super(resultcode,"CompanyProjectBean");
    }

    public CompanyProjector getCompanyProjector() {
        return companyProjector;
    }

    public void setCompanyProjector(CompanyProjector companyProjector) {
        this.companyProjector = companyProjector;
    }
}
