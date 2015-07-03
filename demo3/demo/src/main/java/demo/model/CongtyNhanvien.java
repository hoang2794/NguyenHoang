package demo.model;

import javax.persistence.Id;

/**
 * Created by Nguyen Hoang on 03-Jul-15.
 */
public class CongtyNhanvien extends Congty {
    @Id
    public String employeeid;

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }
}
