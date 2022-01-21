package costcalcapp.controller;

import costcalcapp.ServerWorker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertCalculatingTable {

    private ServerWorker sw;
    private DBWorker mdbc;
    private java.sql.Statement stmt;

    public InsertCalculatingTable(ServerWorker sw) {
        try {
            this.sw = sw;
            mdbc = new DBWorker();
            //mdbc.init();
            Connection conn = mdbc.getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            System.out.println("InsertCalculatingTable = " + e);
        }
    }

    public void SendButtonActionPerformed() throws IOException, ClassNotFoundException {

        sw.stringmap = sw.receiveStringMap();
        String CalculatingID = sw.stringmap.get("CalculatingID");
        String ProductID = sw.stringmap.get("ProductID");
        //String ProductCost = sw.stringmap.get("ProductCost");
        String CalculatingDate = sw.stringmap.get("CalculatingDate");
        String EnergyCosts = sw.stringmap.get("EnergyCosts");
        String Salary = sw.stringmap.get("Salary");
        String ProductCost = calculateProductCost(ProductID, EnergyCosts, Salary);

        String insertStr = "";
        try {
            insertStr = "INSERT INTO calculating(ProductID,ProductCost,CalculatingDate) VALUES ("
                    //+ quotate(ProductID) + ","
                    + quotate(ProductID) + ","
                    + quotate(ProductCost) + ","
                    + quotate(CalculatingDate) + ")";
            int done = stmt.executeUpdate(insertStr);
            //sendMessage("" + done + " row inserted");
        } catch (SQLException e) {
            System.out.println("insert = " + e);
            sw.sendMessage("Error occured in inserting data: " + e);
        }
    }

    public String calculateProductCost(String ProductID, String EnergyCosts, String Salary) {
        Double productCost = 0.0;
        Double materialsCosts = 0.0;
        double energyCosts = Double.parseDouble(EnergyCosts);
        double salary = Double.parseDouble(Salary);

        ResultSet rs = null;
        try {
            ArrayList<String[]> materialsratesList;

            rs = stmt.executeQuery("SELECT * FROM materialsrates "
                    + "WHERE ProductID =" + ProductID);

            materialsratesList = new ArrayList<>();

            while (rs.next()) {
                String[] row = {
                    rs.getString("MaterialRateID"),
                    rs.getString("ProductID"),
                    rs.getString("MaterialID"),
                    rs.getString("MaterialRate")
                };
                materialsratesList.add(row);
            }

            ArrayList<String[]> materialsList;

            rs = stmt.executeQuery("SELECT * FROM materials");

            materialsList = new ArrayList<>();

            while (rs.next()) {
                String[] row = {
                    rs.getString("MaterialID"),
                    rs.getString("MaterialName"),
                    rs.getString("MaterialPrice"),
                    rs.getString("MaterialMeasure")};
                materialsList.add(row);
            }

            for (String[] matrate : materialsratesList) {
                int matIDfromMatrate = Integer.parseInt(matrate[2]);

                for (String[] mat : materialsList) {
                    int matIDfromMat = Integer.parseInt(mat[0]);

                    if (matIDfromMatrate == matIDfromMat) {
                        double materialPrice = Double.parseDouble(mat[2]);
                        double materialRate = Double.parseDouble(matrate[3]);

                        materialsCosts += materialPrice * materialRate;
                        System.out.println("materialCos " + matIDfromMat + "= " + materialPrice * materialRate);
                    }
                }
            }

            rs = stmt.executeQuery("SELECT * FROM company ");

            rs.last();
            String[] companyParameters = {
                rs.getString("CompanyID"),
                rs.getString("CompanyName"),
                rs.getString("VAT"),
                rs.getString("SocialCharges"),
                rs.getString("ManufCosts"),
                rs.getString("GenrunCosts"),
                rs.getString("InsurCosts")
            };

            double socialCharges = Double.parseDouble(companyParameters[3]) * 0.01 * salary;
            double manufCosts = Double.parseDouble(companyParameters[4]) * 0.01 * materialsCosts;
            double genrunCosts = Double.parseDouble(companyParameters[5]) * 0.01 * materialsCosts;
            double insurCosts = Double.parseDouble(companyParameters[6]) * 0.01 * salary;

            productCost = materialsCosts + energyCosts + salary + socialCharges + insurCosts + manufCosts + genrunCosts;

            System.out.println("materialsCosts = " + materialsCosts);
            System.out.println("energyCosts = " + energyCosts);
            System.out.println("salary = " + salary);
            System.out.println("socialCharges = " + socialCharges);
            System.out.println("insurCosts = " + insurCosts);
            System.out.println("manufCosts = " + manufCosts);
            System.out.println("genrunCosts = " + genrunCosts);
            System.out.println("productCost = " + productCost);

            productCost = Double.parseDouble(String.valueOf(Math.round(productCost)));
            //sw.sendResultSet(resultSets);
            rs.close();
        } catch (SQLException e) {
            System.out.println("calculateProductCost = " + e);
        }

        return productCost.toString();
    }

    public void DeleteButtonActionPerformed() throws IOException, ClassNotFoundException {

        sw.stringmap = sw.receiveStringMap();
        String CalculatingID = sw.stringmap.get("CalculatingID");

        String insertStr = "";
        try {
            insertStr = "DELETE FROM calculating WHERE CalculatingID=" + CalculatingID;
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
            String CalculatingID = sw.stringmap.get("CalculatingID");
            String CalculatingDate = sw.stringmap.get("CalculatingDate");

            String insertStr;
            int done;
            insertStr = "UPDATE calculating SET CalculatingDate="
                    + quotate(CalculatingDate) + "WHERE CalculatingID =" + CalculatingID;
            done = stmt.executeUpdate(insertStr);

//            insertStr = "UPDATE calculating SET CalculatingID="
//                    + quotate(CalculatingID) + "WHERE CalculatingID ="
//                    + (String) CalculatingTable.getValueAt(CalculatingTable.getSelectedRow(), 0);
//            done = stmt.executeUpdate(insertStr);
            //sendMessage("row edited");
        } catch (SQLException e) {
            sw.sendMessage("Error occured in editing data:" + e);
        }
    }

    public void getResultFromCalculating() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM calculating");

            ArrayList<String[]> resultSets = new ArrayList<>();

            while (rs.next()) {
                String[] row = {
                    rs.getString("CalculatingID"),
                    rs.getString("ProductID"),
                    rs.getString("ProductCost"),
                    rs.getString("CalculatingDate")
                };
                resultSets.add(row);
            }

            sw.sendResultSet(resultSets);
            rs.close();
        } catch (SQLException e) {
            System.out.println("getResultFromCalculating = " + e);
        }
    }

    public void getResultFromCalculating(String productID) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM calculating WHERE ProductID = " + productID);

            ArrayList<String[]> resultSets = new ArrayList<>();

            while (rs.next()) {
                String[] row = {
                    rs.getString("CalculatingID"),
                    rs.getString("ProductID"),
                    rs.getString("ProductCost"),
                    rs.getString("CalculatingDate")
                };
                resultSets.add(row);
            }

            sw.sendResultSet(resultSets);
            rs.close();
        } catch (SQLException e) {
            System.out.println("getResultFromCalculating = " + e);
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

    public void getNameOfProduct(String productID) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT ProductName FROM products WHERE ProductID = " + productID);

            ArrayList<String[]> resultSets = new ArrayList<>();

            while (rs.next()) {
                String[] row = {
                    rs.getString("ProductName")
                };
                resultSets.add(row);
            }

            sw.sendResultSet(resultSets);
            rs.close();
        } catch (SQLException e) {
            System.out.println("getNameOfProduct = " + e);
        }
    }
    public void getNamesOfProduct() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT ProductName FROM products");

            ArrayList<String[]> resultSets = new ArrayList<>();

            while (rs.next()) {
                String[] row = {
                    rs.getString("ProductName")
                };
                resultSets.add(row);
            }

            sw.sendResultSet(resultSets);
            rs.close();
        } catch (SQLException e) {
            System.out.println("getNameOfProduct = " + e);
        }
    }

    public void getIDsFromProducts() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT ProductID FROM products");

            ArrayList<String[]> resultSets = new ArrayList<>();

            while (rs.next()) {
                String[] row = {
                    rs.getString("ProductID")
                };
                resultSets.add(row);
            }

            sw.sendResultSet(resultSets);
            rs.close();
        } catch (SQLException e) {
            System.out.println("getIDsFromProducts = " + e);
        }
    }

    public String quotate(String content) {
        return " '" + content + "' ";
    }
}
