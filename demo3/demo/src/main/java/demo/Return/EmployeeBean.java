package demo.Return;

import demo.model.Employee;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
public class EmployeeBean extends BeanBasic {
    public Bean bean;
    private List<Employee> employeeList;

    protected EmployeeBean(){}

    public EmployeeBean(Employee employee, Integer resultcode){
        super(resultcode,"EmployeeBean");
        this.bean = new Bean(employee);
    }

    public EmployeeBean(List<Employee> employeeList, Integer resultcode){
        super(resultcode,"EmployeeBean");
        this.employeeList = employeeList;
    }

    public EmployeeBean(Integer resultcode){
        super(resultcode,"EmployeeBean");
    }


    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
