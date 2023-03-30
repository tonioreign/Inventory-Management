package com.inventory.inventorymanagement.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Models a product with associated parts
 *
 * @author Antonio Jenkins
 * */
public class Product{
    /** a list for all associated parts to the product*/
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    /** the id for product*/
    private int id;
    /** the name for product*/
    private String name;
    /** the price for product*/
    private double price;
    /** the inventory level for product*/
    private int stock;
    /** the min for product*/
    private int min;
    /** the max for product*/
    private int max;
    /** Constructor for a new instance of a Product object
     *
     * @param id the ID for product
     * @param name  the name for product
     * @param price the price for product
     * @param stock the inventory level for product
     * @param min the min for part
     * @param max the maximum for product
     * */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    /** setter for id
     *
     * @param id the product ID*/
    public void setId(int id){
        this.id = id;
    }
    /** getter for id
     *
     * @return the product ID*/
    public int getId(){
        return id;
    }
    /** setter for name
     *
     * @param name the products name*/
    public void setName(String name){
        this.name = name;
    }
    /** getter for name
     *
     * @return the products name*/
    public String getName(){
        return name;
    }
    /** setter for price
     *
     * @param price the products price*/
    public void setPrice(double price){
        this.price = price;
    }
    /** getter for price
     *
     * @return the products price*/
    public double getPrice(){
        return price;
    }
    /** setter for stock
     *
     * @param stock gets the products inventory level*/
    public void setStock(int stock){
        this.stock = stock;
    }
    /** getter for stock
     *
     * @return the products inventory level*/
    public int getStock(){
        return stock;
    }
    /** setter for min
     *
     * @param min the products min*/
    public void setMin(int min){
        this.min = min;
    }
    /** getter for min
     *
     * @return the products min*/
    public int getMin(){
        return min;
    }
    /** setter for max
     *
     * @param max the products max*/
    public void setMax(int max){
        this.max = max;
    }
    /** getter for max
     *
     * @return the products max*/
    public int getMax(){
        return max;
    }

    /** @return list of associated parts to product*/
    public ObservableList<Part> getAssociatedParts(){
        return associatedParts;
    }
    /** Adds a part to associated part list
     * @param part the part to be added to associated product list*/
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    /** Deletes an associated part to the product
     *
     * @param part the part to be deteled from associated part list
     * @return a boolean indicating if part was removed from associated part list*/
    public boolean deleteAssociatedPart(Part part){
        if (associatedParts.contains(part)) {
            associatedParts.remove(part);
            return true;
        }
        else
            return false;
    }
    /** @return associated parts to the product*/
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
}
