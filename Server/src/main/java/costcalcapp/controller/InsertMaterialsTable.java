package costcalcapp.controller;

import costcalcapp.ServerWorker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class InsertMaterialsTable {
    
    private ServerWorker sw;
    private DBWorker mdbc;
    private java.sql.Statement stmt;

    public InsertMaterialsTable(ServerWorker sw) {
        try {
            this.sw = sw;
            mdbc = new DBWorker();
            //mdbc.init();
            Connection conn = mdbc.getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            System.out.println("InsertMaterialTable = " + e);
        }
    }

    public void SendButtonActionPerformed() throws IOException, ClassNotFoundException {

        sw.stringmap = sw.receiveStringMap();
        String MaterialID = sw.stringmap.get("MaterialID");
        String MaterialName = sw.stringmap.get("MaterialName");
        String MaterialPrice = sw.stringmap.get("MaterialPrice");
        String MaterialMeasure = sw.stringmap.get("MaterialMeasure");

        String insertStr = "";
        try {
            insertStr = "INSERT INTO materials(MaterialName, MaterialPrice, MaterialMeasure) VALUES ("
                    //+ quotate(MaterialID) + ","
                    + quotate(MaterialName) + ","
                    + quotate(MaterialPrice) + ","
                    + quotate(MaterialMeasure) + ")";
            int done = stmt.executeUpdate(insertStr);
            //sendMessage("" + done + " row inserted");
        } catch (SQLException e) {
            System.out.println("insert = " + e);
            sw.sendMessage("Error occured in inserting data: " + e);
        }
    }

    public void DeleteButtonActionPerformed() throws IOException, ClassNotFoundException {

        sw.stringmap = sw.receiveStringMap();
        String MaterialID = sw.stringmap.get("MaterialID");

        String insertStr = "";
        try {
            insertStr = "DELETE FROM materials WHERE MaterialID=" + MaterialID;
            int done = stmt.executeUpdate(insertStr);
            //sendMessage("" + done + " row deleted");
        } catch (SQLException e) {
            System.out.println("delete = " + e);
            sw.sendMessage("Error occured in deleting data: " + e);
        }
    }

    public void EditButtonActionPerformed() {
        try {
            sw.stringmap = sw.receiveStringMap();
            String MaterialID = sw.stringmap.get("MaterialID");
            String MaterialName = sw.stringmap.get("MaterialName");
            String MaterialPrice = sw.stringmap.get("MaterialPrice");
            String MaterialMeasure = sw.stringmap.get("MaterialMeasure");

            String insertStr;
            int done;
            insertStr = "UPDATE materials SET MaterialName="
                    + quotate(MaterialName) + "WHERE MaterialID =" + MaterialID;
            done = stmt.executeUpdate(insertStr);
            insertStr = "UPDATE materials SET MaterialPrice ="
                    + quotate(MaterialPrice) + "WHERE MaterialID =" + MaterialID;
            done = stmt.executeUpdate(insertStr);
            insertStr = "UPDATE materials SET MaterialMeasure ="
                    + quotate(MaterialMeasure) + "WHERE MaterialID =" + MaterialID;
            done = stmt.executeUpdate(insertStr);

//            insertStr = "UPDATE materials SET MaterialID="
//                    + quotate(dlg.getMaterialID()) + "WHERE MaterialID ="
//                    + (String) MaterialTable.getValueAt(MaterialTable.getSelectedRow(), 0);
//            done = stmt.executeUpdate(insertStr);
            //sendMessage("row edited");
        } catch (SQLException e) {
            sw.sendMessage("Error occured in editing data:" + e);
        }
    }

    public void getResultFromMaterials() throws IOException {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM materials");

            ArrayList<String[]> resultSets = new ArrayList<>();

            while (rs.next()) {
                String[] row = {rs.getString("MaterialID"),
                    rs.getString("MaterialName"),
                    rs.getString("MaterialPrice"),
                    rs.getString("MaterialMeasure")};
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
