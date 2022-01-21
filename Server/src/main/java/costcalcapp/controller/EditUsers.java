package costcalcapp.controller;

import costcalcapp.model.User;
import costcalcapp.ServerWorker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author M4RT1H
 */
public class EditUsers {

    private ServerWorker sw;
    private DBWorker mdbc;
    private java.sql.Statement stmt;
    private User user;

    public EditUsers(ServerWorker sw) {
        try {
            this.sw = sw;
            mdbc = new DBWorker();
            //mdbc.init();
            Connection conn = mdbc.getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            System.out.println("EditUsers = " + e);
        }
    }

    public void EditUsersButton(String CurUserID) throws IOException, ClassNotFoundException {

        String userID = sw.receiveMessage();

        if (CurUserID.equals(userID)) {
            sw.sendMessage("_error_wnd");
            sw.sendMessage((String) ("<html>Вы не можете изменить свои права </html>"));
        } else {
            sw.sendMessage("_rights_edit");
            String check = sw.receiveMessage();
            try {
                // sw.stringmap = sw.receiveStringMap();

                String insertStr;

                insertStr = "UPDATE users SET UserRigths="
                        + quotate(check) + "WHERE UserID =" + userID;
                stmt.executeUpdate(insertStr);
                
                
                
                sw.sendMessage("_error_wnd");
                sw.sendMessage((String) ("<html>Права были изменены! </html>"));

            } catch (SQLException e) {
                sw.sendMessage("Error occured in editing data:" + e);
            }

            //TODO
        }
        sw.sendMessage("_update_components");
    }

    public void DeleteButtonActionPerformed(String CurUserID) throws IOException, ClassNotFoundException {

        String UserID = sw.receiveMessage();

        String insertStr = "";
        String insertStrq = "";
        try {
            if (CurUserID.equals(UserID)) {
                sw.sendMessage("_error_wnd");
                sw.sendMessage((String) ("<html>Вы не можете удалить самого себя!</html>"));

            } else {

                insertStr = "DELETE FROM users WHERE UserID=" + UserID;
                insertStrq = "DELETE FROM employees WHERE EmployeeID=" + UserID;

                stmt.executeUpdate(insertStr);
                stmt.executeUpdate(insertStrq);

                sw.sendMessage("_error_wnd");
                sw.sendMessage((String) ("<html>Пользователь был удален! </html>"));
                // sw.sendMessage("_update_components");
            }
        } catch (SQLException e) {
            System.out.println("delete = " + e);
            sw.sendMessage("Error occured in deleting data: " + e);
        }
        sw.sendMessage("_update_components");
    }

    public void getNameFromUsers() throws IOException {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM employees");

            ArrayList<String[]> resultSets = new ArrayList<>();

            while (rs.next()) {
                String[] row = {rs.getString("EmployeeID"),
                    rs.getString("EmployeeSurname"),
                    rs.getString("EmployeeName")};

                resultSets.add(row);
            }

            sw.sendResultSet(resultSets);
            rs.close();
        } catch (SQLException e) {
            System.out.println("getResultFromMaterials = " + e);
        }
    }

    public String quotate(String content) {
        return " '" + content + "' ";
    }
}
