package com.inventory.inventorymanagement.model;

public class Outsourced extends Part{
    // create company name
    private String companyName;
    // constructor
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    // setting company name
    public void setCompanyName(String companyName){

        this.companyName = companyName;
    }
    // getting company name
    public String getCompanyName(){
        return companyName;
    }
}
