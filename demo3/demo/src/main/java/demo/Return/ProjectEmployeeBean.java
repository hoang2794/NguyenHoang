package demo.Return;

import demo.model.ProjectEmployee;

/**
 * Created by Nguyen Hoang on 05-Jul-15.
 */
public class ProjectEmployeeBean extends BeanBasic {
    private ProjectEmployeeBean projectEmployeeBean;
    public Bean bean;
    public ProjectEmployeeBean(Integer resultcode){
        super(resultcode,"ProjectEmployeeBean");
    }

    public ProjectEmployeeBean(ProjectEmployee projectEmployee, Integer resultcode){
        super(resultcode,"ProjectEmployeeBean");
        this.bean = new Bean(projectEmployee);
    }
    public ProjectEmployeeBean getProjectEmployeeBean() {
        return projectEmployeeBean;
    }

    public void setProjectEmployeeBean(ProjectEmployeeBean projectEmployeeBean) {
        this.projectEmployeeBean = projectEmployeeBean;
    }
}
