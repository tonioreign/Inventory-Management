package com.inventory.inventorymanagement;

import javafx.beans.Observable;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts;

    public void addPart(Part part){

    }

    public static void addProduct(Product product){

    }

    /*public static Part lookupPart(int partId){

    }

    public static Product lookupProduct(int productId){

    }

    public static ObservableList<Part> lookupPart(String partName){

    }

    public static ObservableList<Product> lookupProduct (String productName){

    }*/

    public static void updatePart(int index, Part selectedPart){

    }

    public static void updateProduct(int index, Part newProduct){

    }

    public static boolean deletePart(Part selectedPart){
        return true;
    }

    public static boolean deleteProduct(Part selectedProduct){
        return true;
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
