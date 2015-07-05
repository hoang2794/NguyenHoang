package demo.Return;

/**
 * Created by Nguyen Hoang on 05-Jul-15.
 */
public class ProjectorEmployeeBean extends BeanBasic {
    private ProjectorEmployeeBean projectorEmployeeBean;

    public ProjectorEmployeeBean(Integer resultcode){
        super(resultcode,"ProjectorEmployeeBean");
    }

    public ProjectorEmployeeBean getProjectorEmployeeBean() {
        return projectorEmployeeBean;
    }

    public void setProjectorEmployeeBean(ProjectorEmployeeBean projectorEmployeeBean) {
        this.projectorEmployeeBean = projectorEmployeeBean;
    }
}
