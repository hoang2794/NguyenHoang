package demo.Return;

import demo.model.*;

import java.util.Objects;

/**
 * Created by Nguyen Hoang on 06-Jul-15.
 */
public class Bean {
    private Bean bean;
    public Object Object;

    public Bean(User user){
        this.Object = (User) user;
    }

    public Bean(Company company){
        this.Object = (Company) company;
    }

    public Bean(Employee employee){
        this.Object = (Employee) employee;
    }

    public Bean(Project project){
        this.Object = (Project) project;
    }

    public Bean(Task task){
        this.Object = (Task) task;
    }

    public Bean(CompanyProject companyProject){
        this.Object = (CompanyProject) companyProject;
    }

    public Bean(CompanyEmployee companyEmployee){
        this.Object = (CompanyEmployee) companyEmployee;
    }

    public Bean(ProjectEmployee projectEmployee){
        this.Object = (ProjectEmployee) projectEmployee;
    }
}
