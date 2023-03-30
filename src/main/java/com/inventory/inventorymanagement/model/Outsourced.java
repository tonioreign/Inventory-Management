package com.inventory.inventorymanagement.model;

/**
 * Outsourced for part
 *
 * @author Antonio
 * */
public class Outsourced extends Part{
    /** the company name for the part*/
    private String companyName;
    /** Constructor for a new instance of an Outsourced object
     *
     * @param id the ID for part
     * @param name  the name for part
     * @param price the price for part
     * @param stock the inventory level for part
     * @param min the min for part
     * @param max the maximum for part
     * @param companyName the machine ID for part
     * */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    /** the setter for company name*/
    public void setCompanyName(String companyName){

        this.companyName = companyName;
    }
    /** the getter for company name*/
    public String getCompanyName(){
        return companyName;
    }
}
