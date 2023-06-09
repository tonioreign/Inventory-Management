package com.inventory.inventorymanagement.controller;

import com.inventory.inventorymanagement.model.Inventory;
import com.inventory.inventorymanagement.model.Part;
import com.inventory.inventorymanagement.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * Controller class that provides control logic for main menu of the application.
 *
 * @author Antonio Jenkins
 */
public class MenuController implements Initializable {
    /** part table view*/
    @FXML
    private TableView<Part> partTable;
    /** part table ID*/
    @FXML
    private TableColumn<Part, Integer> partIDCol;
    /** part table name*/
    @FXML
    private TableColumn<Part, String> partNameCol;
    /** part table inventory level*/
    @FXML
    private TableColumn<Part, Integer> partInvLvlCol;
    /** part table cost*/
    @FXML
    private TableColumn<Part, Integer> partCostCol;
    /** add part button*/
    @FXML
    private Button addPart;
    /** mod part button*/
    @FXML
    private Button modPart;
    /** delete part button*/
    @FXML
    private Button deletePart;
    /** product table view*/
    @FXML
    private TableView<Product> productTable;
    /** product table ID*/
    @FXML
    private TableColumn<Product, Integer> productIDCol;
    /** product table name*/
    @FXML
    private TableColumn<Product, String> productNameCol;
    /** product table inventory level*/
    @FXML
    private TableColumn<Product, Integer> productInvLvlCol;
    /** product table cost*/
    @FXML
    private TableColumn<Product, Integer> productCostCol;
    /** add product button*/
    @FXML
    private Button addProduct;
    /** mod product button*/
    @FXML
    private Button modProduct;
    /** delete product button*/
    @FXML
    private Button deleteProduct;
    /** search part text field*/
    @FXML
    private TextField searchPart;
    /** search product text field*/
    @FXML
    private TextField searchProduct;
    /** exit button*/
    @FXML
    private Button exit;
    /** part to be modified (being passed)*/
    private static Part modifyPart;
    /** product to be modified (being passed)*/
    private static Product modifyProduct;

    /** stage to be set*/
    private Stage stage;
    /** scene to be set*/
    private Scene scene;
    private Parent root;

    /**
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     *
     * Ran into an error here stating that this.allParts table was null when the table was filled. Learned that I can't
     * have the same controller referenced to multiple fxml files. RUNTIME ERROR
     *
     * "FUTURE ENHANCEMENT" - SOME CODES I USED A LOT AND OVERUSED, in the future I can make the program more scalable by
     *                       refactoring a lot of reused code into their own separate methods.
     *
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        /** setting each table on the menu screen with its individual category.*/
        partTable.setItems(Inventory.getAllParts());
        productTable.setItems((Inventory.getAllProducts()));
        /** setting the parts columns with their respective attributes.*/
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInvLvlCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("price"));
        /** setting the products columns with their respective attributes.*/
        productIDCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInvLvlCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productCostCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
    }

    /** method loads up the part form - reused this code a lot to transition forms - could make a main method for it in the future
     * @throws IOException*/
    public void onAddPart(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddPart.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** opens the modify form
     * @throws IOException*/
    public void onModPart(ActionEvent e)throws IOException{
        modifyPart = partTable.getSelectionModel().getSelectedItem();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ModifyPart.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /** deletes selected part from parts tables*/
    public void onDeletePart(ActionEvent e){
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(4);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();
            // if OK delete part
            if (result.isPresent() && result.get() == ButtonType.OK) {

                    Inventory.deletePart(selectedPart);
                    partTable.setItems(Inventory.getAllParts());
            }
        }
    }
    /** returning modified part (being passed)*/
    @FXML
    public static Part getModifyPart() {
        return modifyPart;
    }
    /** returning modified product (being passed)*/
    public static Product getModifyProduct() {
        return modifyProduct;
    }
    /** opens add product form
     * @throws IOException*/
    public void onAddProduct(ActionEvent e)throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddProduct.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /** opens modify product form
     * @throws IOException*/
    public void onModProduct(ActionEvent e)throws IOException{

        modifyProduct = productTable.getSelectionModel().getSelectedItem();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ModifyProduct.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /** deletes selected product from products tables - no parts associated
     *
     * delete product from inventory*/
    public void onDeleteProduct(ActionEvent e){
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            displayAlert(4);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                ObservableList<Part> assocParts = selectedProduct.getAllAssociatedParts();
                /** checking associated parts are removed*/
                if (assocParts.size() >= 1) {
                    displayAlert(5);
                } else {
                    Inventory.deleteProduct(selectedProduct); // delete product from inventory
                    productTable.setItems(Inventory.getAllProducts());
                }
            }
        }
    }

    /** search function above "Part Table"
     * getting part name from search fields
     *
     * running into an error - display alert shows to often when populated or entering an invalid part
     *
     * partial fix - checking if null then add to list - new error. not populating all parts
     * WIERD FIX - assigned idPartList to the results of the string lookup and checked for null variables
     * */
    @FXML
    public void onSearchPart(ActionEvent e){
        ObservableList<Part> idPartList = FXCollections.observableArrayList();
        String partStr = searchPart.getText(); // getting part name from search fields

        try{
            /** searching for the string name first*/
            idPartList = Inventory.lookupPart(partStr);
            partTable.setItems(idPartList);
            Integer partId = Integer.parseInt(partStr);
            Part part = Inventory.lookupPart(partId);
            /** try catch block to catch any errors if the string cannot be parsed into an integer*/
            if(part != null) {    // partial fix - checking if null then add to list - new error. not populating all parts
                idPartList.add(part);
                partTable.setItems(idPartList);
            }
        }catch(NumberFormatException i){
            // ignore
        }

        if(idPartList.size() == 0){
            displayAlert(1);
        }

        if(partStr.isEmpty() || partStr.isBlank())
        {
            partTable.setItems(Inventory.getAllParts());
        }
    }
    /**search function above "Product Table"
     * getting part name from search fields
     *
     * running into an error - display alert shows to often when populated or entering an invalid part
     *  WIERD FIX - assigned idPartList to the results of the string lookup and checked for null variables
     * */
    @FXML
    public void onSearchProd(ActionEvent e){
        ObservableList<Product> idList = FXCollections.observableArrayList();
        String prodStr = searchProduct.getText(); // getting part name from search fields

        try{
            /** searching for the string name first*/
            idList = Inventory.lookupProduct(prodStr);
            productTable.setItems(idList);
            Integer prodId = Integer.parseInt(prodStr);
            Product prod = Inventory.lookupProduct(prodId);
            /** try catch block to catch any errors if the string cannot be parsed into an integer*/
            if(prod != null) {    // partial fix - checking if null then add to list - new error. not populating all parts
                idList.add(prod);
                productTable.setItems(idList);
            }
        }catch(NumberFormatException i){
            // ignore
        }

        if(idList.size() == 0){
            displayAlert(2);
        }

        if(prodStr.isEmpty() || prodStr.isBlank())
        {
            productTable.setItems(Inventory.getAllProducts());
        }
    }

    /** cancel function - return back to menu
     * @throws IOException*/
    public void cancelBtn(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** alerts created to display specific errors
     *
     * @param alertType the number associated with an alert*/
    public void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Alert alertError = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("Information");
                alert.setHeaderText("Part not found");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Information");
                alert.setHeaderText("Product not found");
                alert.showAndWait();
                break;
            case 3:
                alertError.setTitle("Error");
                alertError.setHeaderText("Part not selected");
                alertError.showAndWait();
                break;
            case 4:
                alertError.setTitle("Error");
                alertError.setHeaderText("Product not selected");
                alertError.showAndWait();
                break;
            case 5:
                alertError.setTitle("Error");
                alertError.setHeaderText("Parts Associated");
                alertError.setContentText("All parts must be removed from product before deletion.");
                alertError.showAndWait();
                break;
        }
    }

    /** closes the menu*/
    public void exitBtn(ActionEvent e){
        System.exit(0);
    }
}
