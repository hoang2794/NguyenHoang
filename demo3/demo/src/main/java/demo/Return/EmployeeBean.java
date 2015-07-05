package demo.Return;

import demo.model.Employee;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
public class EmployeeBean extends BeanBasic {
    private Employee employee;

    private List<Employee> employeeList;

    protected EmployeeBean(){}

    public EmployeeBean(Employee employee, Integer resultcode){
        super(resultcode,"EmployeeBean");
        this.employee = employee;
    }

    public EmployeeBean(List<Employee> employeeList, Integer resultcode){
        super(resultcode,"EmployeeBean");
        this.employeeList = employeeList;
    }

    public EmployeeBean(Integer resultcode){
        super(resultcode,"EmployeeBean");
        this.employee =null;
        this.employeeList =null;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
