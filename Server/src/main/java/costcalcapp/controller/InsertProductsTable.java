package costcalcapp.controller;


import costcalcapp.ServerWorker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertProductsTable {

    private ServerWorker sw;
    private DBWorker mdbc;
    private java.sql.Statement stmt;

    public InsertProductsTable(ServerWorker sw) {
        try {
            this.sw = sw;
            mdbc = new DBWorker();
            //mdbc.init();
            Connection conn = mdbc.getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            System.out.println("InsertProductTable = " + e);
        }
    }

    public void SendButtonActionPerformed() throws IOException, ClassNotFoundException {

        sw.stringmap = sw.receiveStringMap();
        String ProductID = sw.stringmap.get("ProductID");
        String ProductName = sw.stringmap.get("ProductName");

        String insertStr = "";
        try {
            insertStr = "INSERT INTO products(ProductName) VALUES ("
                    //+ quotate(ProductID) + ","
                    + quotate(ProductName) + ")";
            int done = stmt.executeUpdate(insertStr);
            //sendMessage("" + done + " row inserted");
        } catch (SQLException e) {
            System.out.println("insert = " + e);
            sw.sendMessage("Error occured in inserting data: " + e);
        }
    }
    
    public void SendButton1ActionPerformed() throws IOException, ClassNotFoundException {

        sw.stringmap = sw.receiveStringMap();
        String ProductID = sw.stringmap.get("ProductID");
        String MaterialID = sw.stringmap.get("MaterialID");
        String MaterialRate = sw.stringmap.get("MaterialRate");

        String insertStr = "";
        try {
            insertStr = "INSERT INTO materialsrates(ProductID,MaterialID,MaterialRate) VALUES ("
                    //+ quotate(MaterialRateID) + ","
                    + quotate(ProductID) + ","
                    + quotate(MaterialID) + ","
                    + quotate(MaterialRate) + ")";
            int done = stmt.executeUpdate(insertStr);
            //sendMessage("" + done + " row inserted");
        } catch (SQLException e) {
            System.out.println("insert = " + e);
            sw.sendMessage("Error occured in inserting data: " + e);
        }
    }

    public void DeleteButtonActionPerformed() throws IOException, ClassNotFoundException {

        sw.stringmap = sw.receiveStringMap();
        String ProductID = sw.stringmap.get("ProductID");

        String insertStr = "";
        try {
            insertStr = "DELETE FROM products WHERE ProductID=" + ProductID;
            int done = stmt.executeUpdate(insertStr);
            //sendMessage("" + done + " row deleted");
        } catch (SQLException e) {
            System.out.println("delete = " + e);
            sw.sendMessage("Error occured in deleting data: " + e);
        }
    }
    public void DeleteButton1ActionPerformed() throws IOException, ClassNotFoundException {

        sw.stringmap = sw.receiveStringMap();
        String MaterialRateID = sw.stringmap.get("MaterialRateID");

        String insertStr = "";
        try {
            insertStr = "DELETE FROM materialsrates WHERE MaterialRateID=" + MaterialRateID;
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
            String ProductID = sw.stringmap.get("ProductID");
            String ProductName = sw.stringmap.get("ProductName");

            String insertStr;
            int done;
            insertStr = "UPDATE products SET ProductName="
                    + quotate(ProductName) + "WHERE ProductID =" + ProductID;
            done = stmt.executeUpdate(insertStr);

//            insertStr = "UPDATE products SET ProductID="
//                    + quotate(dlg.getProductID()) + "WHERE ProductID ="
//                    + (String) ProductTable.getValueAt(ProductTable.getSelectedRow(), 0);
//            done = stmt.executeUpdate(insertStr);
            //sendMessage("row edited");
        } catch (SQLException e) {
            sw.sendMessage("Error occured in editing data:" + e);
        }
    }

    public void EditButton1ActionPerformed() {
        try {
            sw.stringmap = sw.receiveStringMap();
            String MaterialRateID = sw.stringmap.get("MaterialRateID");
            String MaterialID = sw.stringmap.get("MaterialID");
            String MaterialRate = sw.stringmap.get("MaterialRate");

            String insertStr;
            int done;
            insertStr = "UPDATE materialsrates SET MaterialID="
                    + quotate(MaterialID) + "WHERE MaterialRateID =" + MaterialRateID;
            done = stmt.executeUpdate(insertStr);
            insertStr = "UPDATE materialsrates SET MaterialRate="
                    + quotate(MaterialRate) + "WHERE MaterialRateID =" + MaterialRateID;
            done = stmt.executeUpdate(insertStr);

//            insertStr = "UPDATE products SET ProductID="
//                    + quotate(dlg.getProductID()) + "WHERE ProductID ="
//                    + (String) ProductTable.getValueAt(ProductTable.getSelectedRow(), 0);
//            done = stmt.executeUpdate(insertStr);
            //sendMessage("row edited");
        } catch (SQLException e) {
            sw.sendMessage("Error occured in editing data:" + e);
        }
    }

    public void getResultFromProducts() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM products");

            ArrayList<String[]> resultSets = new ArrayList<>();

            while (rs.next()) {
                String[] row = {
                    rs.getString("ProductID"),
                    rs.getString("ProductName")
                };
                resultSets.add(row);
            }

            sw.sendResultSet(resultSets);
            rs.close();
        } catch (SQLException e) {
            System.out.println("getResultFromProducts = " + e);
        }
    }

    public void getResultFromMaterialsRates() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM materialsrates");

            ArrayList<String[]> resultSets = new ArrayList<>();

            while (rs.next()) {
                String[] row = {
                    rs.getString("MaterialRateID"),
                    rs.getString("ProductID"),
                    rs.getString("MaterialID"),
                    rs.getString("MaterialRate")
                };
                resultSets.add(row);
            }

            sw.sendResultSet(resultSets);
            rs.close();
        } catch (SQLException e) {
            System.out.println("getResultFromMaterialsRates = " + e);
        }
    }

    public void getIDsFromMaterials() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT MaterialID FROM materials");

            ArrayList<String[]> resultSets = new ArrayList<>();

            while (rs.next()) {
                String[] row = {
                    rs.getString("MaterialID")
                };
                resultSets.add(row);
            }

            sw.sendResultSet(resultSets);
            rs.close();
        } catch (SQLException e) {
            System.out.println("getIDsFromMaterials = " + e);
        }
    }

    public String quotate(String content) {
        return " '" + content + "' ";
    }
}
