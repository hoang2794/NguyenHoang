package demo.Return;

import demo.model.Project;

import java.util.List;

/**
 * Created by Nguyen Hoang on 05-Jul-15.
 */
public class ProjectBean extends BeanBasic {
    public Bean bean;
    List<Project> projectList;
    protected ProjectBean(){}

    public ProjectBean(Integer resultcode){
        super(resultcode,"ProjectBean");
    }

    public ProjectBean(Project project,Integer resultcode){
        super(resultcode,"ProjectBean");
        this.bean = new Bean(project);
    }

    public ProjectBean(List<Project> projectList,Integer resultcode){
        super(resultcode,"ProjectBean");
        this.projectList=projectList;
    }


    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}
