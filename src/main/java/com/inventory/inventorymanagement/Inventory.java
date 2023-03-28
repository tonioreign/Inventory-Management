package com.inventory.inventorymanagement;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part){
        allParts.add(part);
    }

    public static void addProduct(Product product){
        allProducts.add(product);
    }

    public static Part lookupPart(int partId){
        int idx = 0;
        for(Part part : allParts){
            if(part.getId() == partId) break;
            idx++;
        }
        return allParts.get(idx);
    }

    public static Product lookupProduct(int productId){
        int idx = 0;
        for(Product product : allProducts){
            if(product.getId() == productId) break;
            idx++;
        }
        return allProducts.get(idx);
    }

    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> partialParts = FXCollections.observableArrayList();
        for(Part part: allParts){
            if(part.getName().contains(partName)){
                partialParts.add(part);
            }
        }
        return partialParts;
    }

    public static ObservableList<Product> lookupProduct (String productName){
        ObservableList<Product> partialProducts = FXCollections.observableArrayList();
        for(Product product: allProducts){
            if(product.getName().contains(productName)){
                partialProducts.add(product);
            }
        }
        return partialProducts;
    }

    public static void updatePart(int index, Part selectedPart){


    }

    public static void updateProduct(int index, Product newProduct){

    }

    public static boolean deletePart(Part selectedPart){
        boolean flag = false;

        for(Part part : allParts){
            if(part == selectedPart){
                allParts.remove(part);
                flag = true;
            }
        }
        if(flag == true){
            System.out.println("The part has been successfully deleted!");
        } else System.out.println("The part was not found.");
        return flag;
    }

    public static boolean deleteProduct(Product selectedProduct){
        boolean flag = false;

        for(Product prod : allProducts){
            if(prod == selectedProduct){
                allParts.remove(prod);
                flag = true;
            }
        }
        if(flag == true){
            System.out.println("The product has been successfully deleted!");
        } else System.out.println("The product was not found.");
        return flag;
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
