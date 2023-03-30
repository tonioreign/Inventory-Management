package com.inventory.inventorymanagement;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
public class Inventory {
    // list for all parts
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    // list for all products
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    // adds part to all parts list
    public static void addPart(Part part){
        allParts.add(part);
    }
    // adds product to all product list
    public static void addProduct(Product product){
        allProducts.add(product);
    }
    // looks up part with part ID
    public static Part lookupPart(int partId){
        for(int i = 0; i < allParts.size(); i++){
            Part newPart = allParts.get(i);

            if(newPart.getId() == partId){
                return newPart;
            }
        }
        return null;
    }
    // looks up product with product ID
    public static Product lookupProduct(int productId){
        int idx = 0;
        for(Product product : allProducts){
            if(product.getId() == productId) break;
            idx++;
        }
        return allProducts.get(idx);
    }
    // looks up part with part name
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> partialParts = FXCollections.observableArrayList();
        for(Part part: allParts){
            if(part.getName().contains(partName)){
                partialParts.add(part);
            }
        }
        return partialParts;
    }
    // looks up product with product name
    public static ObservableList<Product> lookupProduct (String productName){
        ObservableList<Product> partialProducts = FXCollections.observableArrayList();
        for(Product product: allProducts){
            if(product.getName().contains(productName)){
                partialProducts.add(product);
            }
        }
        return partialProducts;
    }
    // updates the selected part
    public static void updatePart(int index, Part selectedPart){
        index = allParts.indexOf(selectedPart);
        allParts.set(index, selectedPart);
    }

    // updates the selected product
    public static void updateProduct(int index, Product newProduct){
        index = allProducts.indexOf(newProduct);
        allProducts.set(index, newProduct);
    }
    // removes a deleted product from part list
    public static boolean deletePart(Part selectedPart){
        for(Part part : allParts) {
            allParts.remove(selectedPart);
            return true;
        }
        return false;
    }
    // removes a deleted product from products list
    public static boolean deleteProduct(Product selectedProduct){
        for(Product product : allProducts) {
            allProducts.remove(selectedProduct);
            return true;
        }
        return false;
    }
    // returns all parts
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    // returns all products
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
