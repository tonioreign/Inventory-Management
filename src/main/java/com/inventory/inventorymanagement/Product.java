package com.inventory.inventorymanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product{
    // create associated part list
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    // create product id
    private int id;
    // create product name
    private String name;
    // create product price
    private double price;
    // create product stock
    private int stock;
    // create product min
    private int min;
    //  create product max
    private int max;
    // constructor
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    // setting id
    public void setId(int id){
        this.id = id;
    }
    // getting id
    public int getId(){
        return id;
    }
    // setting name
    public void setName(String name){
        this.name = name;
    }
    // getting name
    public String getName(){
        return name;
    }
    // setting price
    public void setPrice(double price){
        this.price = price;
    }
    // getting price
    public double getPrice(){
        return price;
    }
    // setting stock
    public void setStock(int stock){
        this.stock = stock;
    }
    // getting stock
    public int getStock(){
        return stock;
    }
    // setting min
    public void setMin(int min){
        this.min = min;
    }
    // getting mind
    public int getMin(){
        return min;
    }
    // setting max
    public void setMax(int max){
        this.max = max;
    }
    // getting max
    public int getMax(){
        return max;
    }

    // getting associated parts
    public ObservableList<Part> getAssociatedParts(){
        return associatedParts;
    }
    // adding associated part
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    // delete associated part
    public boolean deleteAssociatedPart(Part part){
        if (associatedParts.contains(part)) {
            associatedParts.remove(part);
            return true;
        }
        else
            return false;
    }
    // returning associated parts
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
}
