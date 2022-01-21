/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costcalcapp.model;

import costcalcapp.model.Material;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author M4RT1H
 */
public class Product {

    private int productID;
    private String productName;
    private List<Material> productMaterials;
    private Map<Integer, Double> productMaterialRates;

    public Product(int productID, String productName, List<Material> productMaterials, Map<Integer, Double> productMaterialRates) {
        this.productID = productID;
        this.productName = productName;
        this.productMaterials = productMaterials;
        this.productMaterialRates = productMaterialRates;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Material> getProductMaterials() {
        return productMaterials;
    }

    public void setProductMaterials(List<Material> productMaterials) {
        this.productMaterials = productMaterials;
    }

    public Map<Integer, Double> getProductMaterialRates() {
        return productMaterialRates;
    }

    public void setProductMaterialRates(Map<Integer, Double> productMaterialRates) {
        this.productMaterialRates = productMaterialRates;
    }

    public double calculateProductCost() {
        double cost = 0.0;
        for (Material productMaterial : productMaterials) {
            cost += productMaterial.calculateCost(productMaterialRates.get(productMaterial.getMaterialID()));
        }
        return cost;
    }

    public void printMaterialsList() {
        for (Material productMaterial : productMaterials) {
            System.out.println(productMaterial.getMaterialName() + " x"
                    + productMaterialRates.get(productMaterial.getMaterialID())
                    + " " + productMaterial.getMaterialMeasure());
        }
    }

    @Override
    public String toString() {
        return ("Продукт: " + productName
                + " (" + Math.round(calculateProductCost()) + "руб. )");
    }

}
