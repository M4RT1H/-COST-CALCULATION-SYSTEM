/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costcalcapp.model;

import static costcalcapp.ServerWorker.encryptUserPassword;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author M4RT1H
 */
public class User {

    private int userID;
    private String userLogin;
    private String userPassword;
    private String userRights;
    private int employeeID;

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRights() {
        return userRights;
    }

    public void setUserRights(String userRights) {
        this.userRights = userRights;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void stringmapToUser(Map<String, String> stringmap) {
//        if (stringmap.get("UserID")!=null) {
//            userID = Integer.parseInt(stringmap.get("UserID"));
//        }
        if (!stringmap.get("UserLogin").isEmpty()) {
            userLogin = stringmap.get("UserLogin");
        }
        if (!stringmap.get("UserPassword").isEmpty()) {
            userPassword = encryptUserPassword(stringmap.get("UserPassword"));
        }
//        if (!stringmap.get("EmployeeID").isEmpty()) {
//            employeeID = Integer.parseInt(stringmap.get("EmployeeID"));
//        }
    }

    public void resultSetToUser(ResultSet resultSet) throws SQLException {
        userID = resultSet.getInt("UserID");
        userLogin = resultSet.getString("UserLogin");
        userPassword = resultSet.getString("UserPassword");
        userRights = resultSet.getString("UserRigths");//Rigths вместо Rights
        employeeID = resultSet.getInt("EmployeeID");
    }

}
