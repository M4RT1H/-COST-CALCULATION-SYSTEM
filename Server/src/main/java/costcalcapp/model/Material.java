/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costcalcapp.model;

/**
 *
 * @author M4RT1H
 */
public class Material {

    private int materialID;
    private String materialName;
    private double materialPrice;
    private String materialMeasure;

    public Material(int materialID, String materialName, double materialPrice, String materialMeasure) {
        this.materialID = materialID;
        this.materialName = materialName;
        this.materialPrice = materialPrice;
        this.materialMeasure = materialMeasure;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public double getMaterialPrice() {
        return materialPrice;
    }

    public String getMaterialMeasure() {
        return materialMeasure;
    }

    public void setMaterialMeasure(String materialMeasure) {
        this.materialMeasure = materialMeasure;
    }

    public void setMaterialPrice(double materialPrice) {
        this.materialPrice = materialPrice;
    }

    public double calculateCost(double norm) {
        return (this.materialPrice * norm);
    }

    @Override
    public String toString() {
        return ("Материал: " + materialName + " (" + materialPrice + "руб. за " + materialMeasure + ")");
    }
}
