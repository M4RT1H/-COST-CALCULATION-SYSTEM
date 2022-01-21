/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costcalcapp.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author M4RT1H
 */
public class Company {

    private String companyName;
    private String companyLocation;
    private Double VAT;//НДС
    private Double SocialCharges;//Отчисления на соц. нужды
    private Double ManufCosts;//Общепроизводств. расходы
    private Double GenrunCosts;//Общехоз. расходы
    private Double InsurCosts;//Обяз. страх. от несч. случаев
    private List<Material> materials;
    private List<Product> products;

    public Company() {
        this.companyName = null;
        this.companyLocation = null;
        this.VAT = null;
        this.SocialCharges = null;
        this.ManufCosts = null;
        this.GenrunCosts = null;
        this.InsurCosts = null;
        this.materials = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public Company(String companyName, String companyLocation, Double VAT, Double SocialCharges, Double ManufCosts, Double GenrunCosts, Double InsurCosts, List<Material> materials, List<Product> products) {
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.VAT = VAT;
        this.SocialCharges = SocialCharges;
        this.ManufCosts = ManufCosts;
        this.GenrunCosts = GenrunCosts;
        this.InsurCosts = InsurCosts;
        this.materials = materials;
        this.products = products;
    }

    public Company(String companyName, String companyLocation, List<Material> materials, List<Product> products) {
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.materials = materials;
        this.products = products;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void printMaterialsList() {
        for (Material material : materials) {
            System.out.println(material);
        }
    }

    public void printProductsList() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public Double getVAT() {
        return VAT;
    }

    public void setVAT(Double VAT) {
        this.VAT = VAT;
    }

    public Double getSocialCharges() {
        return SocialCharges;
    }

    public void setSocialCharges(Double SocialCharges) {
        this.SocialCharges = SocialCharges;
    }

    public Double getManufCosts() {
        return ManufCosts;
    }

    public void setManufCosts(Double ManufCosts) {
        this.ManufCosts = ManufCosts;
    }

    public Double getGenrunCosts() {
        return GenrunCosts;
    }

    public void setGenrunCosts(Double GenrunCosts) {
        this.GenrunCosts = GenrunCosts;
    }

    public Double getInsurCosts() {
        return InsurCosts;
    }

    public void setInsurCosts(Double InsurCosts) {
        this.InsurCosts = InsurCosts;
    }

    public void stringmapToCompany(Map<String, String> stringmap) {
        if (!stringmap.get("CompanyName").isEmpty()) {
            companyName = stringmap.get("CompanyName");
        }
        if (!stringmap.get("CompanyLocation").isEmpty()) {
            companyLocation = stringmap.get("CompanyLocation");
        }
        if (!stringmap.get("VAT").isEmpty()) {
            VAT = Double.parseDouble(stringmap.get("VAT"));
        }
        if (!stringmap.get("SocialCharges").isEmpty()) {
            SocialCharges = Double.parseDouble(stringmap.get("SocialCharges"));
        }
        if (!stringmap.get("ManufCosts").isEmpty()) {
            ManufCosts = Double.parseDouble(stringmap.get("ManufCosts"));
        }
        if (!stringmap.get("GenrunCosts").isEmpty()) {
            GenrunCosts = Double.parseDouble(stringmap.get("GenrunCosts"));
        }
        if (!stringmap.get("InsurCosts").isEmpty()) {
            InsurCosts = Double.parseDouble(stringmap.get("InsurCosts"));
        }
    }

    public void resultSetToCompany(ResultSet resultSet) throws SQLException {
        if (resultSet.last() == true) {
            companyName = resultSet.getString(2);
            companyLocation = resultSet.getString(3);
            VAT = resultSet.getDouble(4);
            SocialCharges = resultSet.getDouble(5);
            ManufCosts = resultSet.getDouble(6);
            GenrunCosts = resultSet.getDouble(7);
            InsurCosts = resultSet.getDouble(8);
        }
    }
}
