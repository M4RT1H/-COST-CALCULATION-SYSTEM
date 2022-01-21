/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costcalcapp.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author M4RT1H
 */
public class Employee {

    private int employeeID;
    private String employeeName;
    private String employeeSurname;

    public Employee() {
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public void stringmapToEmployee(Map<String, String> stringmap) {
//        if (!stringmap.get("EmployeeID").isEmpty()) {
//            employeeID = Integer.parseInt(stringmap.get("EmployeeID"));
//        }
        if (!stringmap.get("EmployeeName").isEmpty()) {
            employeeName = stringmap.get("EmployeeName");
        }
        if (!stringmap.get("EmployeeSurname").isEmpty()) {
            employeeSurname = stringmap.get("EmployeeSurname");
        }
    }

    public void resultSetToEmployee(ResultSet resultSet) throws SQLException {
        if (resultSet.last() == true) {
            employeeID = resultSet.getInt("EmployeeID");
            employeeName = resultSet.getString("EmployeeName");
            employeeSurname = resultSet.getString("EmployeeSurname");
        }
    }

}
