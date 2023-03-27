package com.inventory.inventorymanagement;

import javafx.collections.ObservableList;

public class Product extends Part {

    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    public void addAssociatedPart(Part part){

    }

    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        return true;
    }

    //public ObservableList<Part> getAllAssociatedParts(){

    //}
}
