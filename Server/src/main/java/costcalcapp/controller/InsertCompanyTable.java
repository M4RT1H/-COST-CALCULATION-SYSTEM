package costcalcapp.controller;

import costcalcapp.ServerWorker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertCompanyTable {

    private ServerWorker sw;
    private DBWorker mdbc;
    private java.sql.Statement stmt;

    public InsertCompanyTable(ServerWorker sw) {
        try {
            this.sw = sw;
            mdbc = new DBWorker();
            //mdbc.init();
            Connection conn = mdbc.getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            System.out.println("InsertCompanyTable = " + e);
        }
    }

    public void SendButtonActionPerformed() throws IOException, ClassNotFoundException {

        sw.stringmap = sw.receiveStringMap();
        //String CompanyID = sw.stringmap.get("CompanyID");
        String CompanyName = sw.stringmap.get("CompanyName");
        String CompanyLocation = sw.stringmap.get("CompanyLocation");
        String VAT = sw.stringmap.get("VAT");
        String SocialCharges = sw.stringmap.get("SocialCharges");
        String ManufCosts = sw.stringmap.get("ManufCosts");
        String GenrunCosts = sw.stringmap.get("GenrunCosts");
        String InsurCosts = sw.stringmap.get("InsurCosts");

        String insertStr = "";
        try {
            insertStr = "INSERT INTO company(CompanyName,CompanyLocation,VAT,"
                    + "SocialCharges,ManufCosts,GenrunCosts,InsurCosts) "
                    + "VALUES ("
                    //+ quotate(CompanyID) + ","
                    + quotate(CompanyName) + ","
                    + quotate(CompanyLocation) + ","
                    + quotate(VAT) + ","
                    + quotate(SocialCharges) + ","
                    + quotate(ManufCosts) + ","
                    + quotate(GenrunCosts) + ","
                    + quotate(InsurCosts) + ")";
            int done = stmt.executeUpdate(insertStr);
            //sendMessage("" + done + " row inserted");
        } catch (SQLException e) {
            System.out.println("insert = " + e);
            sw.sendMessage("Error occured in inserting data: " + e);
        }
    }

    public void DeleteButtonActionPerformed() throws IOException, ClassNotFoundException {

        sw.stringmap = sw.receiveStringMap();
        String CompanyID = sw.stringmap.get("CompanyID");

        String insertStr = "";
        try {
            insertStr = "DELETE FROM company WHERE CompanyID=" + CompanyID;
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
            String CompanyID = sw.stringmap.get("CompanyID");
            String CompanyName = sw.stringmap.get("CompanyName");
            String CompanyLocation = sw.stringmap.get("CompanyLocation");
            String VAT = sw.stringmap.get("VAT");
            String SocialCharges = sw.stringmap.get("SocialCharges");
            String ManufCosts = sw.stringmap.get("ManufCosts");
            String GenrunCosts = sw.stringmap.get("GenrunCosts");
            String InsurCosts = sw.stringmap.get("InsurCosts");

            String insertStr;
            int done;
            insertStr = "UPDATE company SET CompanyName="
                    + quotate(CompanyName) + "WHERE CompanyID =" + CompanyID;
            done = stmt.executeUpdate(insertStr);
            insertStr = "UPDATE company SET CompanyLocation ="
                    + quotate(CompanyLocation) + "WHERE CompanyID =" + CompanyID;
            done = stmt.executeUpdate(insertStr);
            insertStr = "UPDATE company SET VAT ="
                    + quotate(VAT) + "WHERE CompanyID =" + CompanyID;
            done = stmt.executeUpdate(insertStr);
            insertStr = "UPDATE company SET SocialCharges ="
                    + quotate(SocialCharges) + "WHERE CompanyID =" + CompanyID;
            done = stmt.executeUpdate(insertStr);
            insertStr = "UPDATE company SET ManufCosts ="
                    + quotate(ManufCosts) + "WHERE CompanyID =" + CompanyID;
            done = stmt.executeUpdate(insertStr);
            insertStr = "UPDATE company SET GenrunCosts ="
                    + quotate(GenrunCosts) + "WHERE CompanyID =" + CompanyID;
            done = stmt.executeUpdate(insertStr);
            insertStr = "UPDATE company SET InsurCosts ="
                    + quotate(InsurCosts) + "WHERE CompanyID =" + CompanyID;
            done = stmt.executeUpdate(insertStr);

//            insertStr = "UPDATE company SET CompanyID="
//                    + quotate(dlg.getCompanyID()) + "WHERE CompanyID ="
//                    + (String) CompanyTable.getValueAt(CompanyTable.getSelectedRow(), 0);
//            done = stmt.executeUpdate(insertStr);
            //sendMessage("row edited");
        } catch (SQLException e) {
            sw.sendMessage("Error occured in editing data:" + e);
        }
    }

    public void getResultFromCompany() throws IOException {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM company");

            ArrayList<String[]> resultSets = new ArrayList<>();

            while (rs.next()) {
                String[] row = {rs.getString("CompanyID"),
                    rs.getString("CompanyName"),
                    rs.getString("CompanyLocation"),
                    rs.getString("VAT"),
                    rs.getString("SocialCharges"),
                    rs.getString("ManufCosts"),
                    rs.getString("GenrunCosts"),
                    rs.getString("InsurCosts")};
                resultSets.add(row);
            }

            sw.sendResultSet(resultSets);
            rs.close();
        } catch (SQLException e) {
            System.out.println("getResultFromCompany = " + e);
        }
    }

    public String quotate(String content) {
        return " '" + content + "' ";
    }
}
