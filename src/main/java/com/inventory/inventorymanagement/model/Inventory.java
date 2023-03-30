package com.inventory.inventorymanagement.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory to hold parts and products
 *
 * @author Antonio
 * */
public class Inventory {
    /** list for all the parts created*/
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /** list for all the products created*/
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    /** adds part to all parts list*/
    public static void addPart(Part part){
        allParts.add(part);
    }
    /** adds product to all product list*/
    public static void addProduct(Product product){
        allProducts.add(product);
    }
    /** looks up part with part ID*/
    public static Part lookupPart(int partId){
        for(int i = 0; i < allParts.size(); i++){
            Part newPart = allParts.get(i);

            if(newPart.getId() == partId){
                return newPart;
            }
        }
        return null;
    }
    /** Looks up product with product ID
     *
     * @param productId the ID of the product
     * @return a product indicating a product was found
     *
     * "RUNTIME ERROR" - *ran into an error where display error wouldn't show up
     * when dependent on the null factor (my searchProduct method)
    * fixed it by adding the "return null" */
    public static Product lookupProduct(int productId){
        for(int i = 0; i < allProducts.size(); i++){
            Product newProd = allProducts.get(i);

            if(newProd.getId() == productId){
                return newProd;
            }
        }
        return null;
    }
    /** Looks up part with part name
     *
     * @param partName the name of the part
     * @return a list of parts with a partial name
     * */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> partialParts = FXCollections.observableArrayList();
        for(Part part: allParts){
            if(part.getName().contains(partName)){
                partialParts.add(part);
            }
        }
        return partialParts;
    }
    /** Looks up product with product name
     *
     * @param productName the name of the product
     * @return a list of products with the full or partial name
     * */
    public static ObservableList<Product> lookupProduct (String productName){
        ObservableList<Product> partialProducts = FXCollections.observableArrayList();
        for(Product product: allProducts){
            if(product.getName().contains(productName)){
                partialProducts.add(product);
            }
        }
        return partialProducts;
    }
    /** Updates the selected part
     *
     * @param index the index of the part to be placed
     * @param selectedPart the part selected to be updated*/
    public static void updatePart(int index, Part selectedPart){
        index = allParts.indexOf(selectedPart);
        allParts.set(index, selectedPart);
    }

    /** Updates the selected product
     *
     * @param index the index of the part to be placed
     * @param newProduct the selected product to be updated */
    public static void updateProduct(int index, Product newProduct){
        index = allProducts.indexOf(newProduct);
        allProducts.set(index, newProduct);
    }
    /** Remove a deleted product from part list
     *
     * @param selectedPart the selected part to be deleted
     * @return indicates a boolean if the part was removed or not*/
    public static boolean deletePart(Part selectedPart){
        for(Part part : allParts) {
            allParts.remove(selectedPart);
            return true;
        }
        return false;
    }
    /** Removes a deleted product from products list
     *
     * @param selectedProduct the selected product to be deleted
     * @return indicates a boolean if the product was removed or not*/
    public static boolean deleteProduct(Product selectedProduct){
        for(Product product : allProducts) {
            allProducts.remove(selectedProduct);
            return true;
        }
        return false;
    }
    /** Gets all the parts in the list
     *
     * @return a list of all the parts*/
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    /** Gets all the products in the list
     *
     * @return a list of all the products*/
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
