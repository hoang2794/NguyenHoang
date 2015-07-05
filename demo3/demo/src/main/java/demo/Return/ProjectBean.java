package demo.Return;

import demo.model.Project;

import java.util.List;

/**
 * Created by Nguyen Hoang on 05-Jul-15.
 */
public class ProjectBean extends BeanBasic {
    private Project project;

    List<Project> projectList;
    protected ProjectBean(){}

    public ProjectBean(Integer resultcode){
        super(resultcode,"ProjectBean");
    }

    public ProjectBean(Project project,Integer resultcode){
        super(resultcode,"ProjectBean");
        this.project=project;
    }

    public ProjectBean(List<Project> projectList,Integer resultcode){
        super(resultcode,"ProjectBean");
        this.projectList=projectList;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}
