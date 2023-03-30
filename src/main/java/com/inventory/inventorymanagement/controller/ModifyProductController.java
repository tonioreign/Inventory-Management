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
 * Controller class that provides control logic for the modify product screen of the application.
 *
 * "RUNTIME ERROR" - running into an error - display alert shows to often when populated or entering an invalid part
 * WIERD FIX - assigned idPartList to the results of the string lookup and checked for null variables
 *
 *
 * "FUTURE ENHANCEMENT" - Be able to request specific parts to be added that could be associated with the product
 * @author Antonio Jenkins
 */
public class ModifyProductController implements Initializable {
    /** setting stage*/
    private Stage stage;
    /** setting scence*/
    private Scene scene;
    /** parent root variable*/
    private Parent root;
    /** product list size*/
    private Integer total = Inventory.getAllProducts().size();
    /** product id text*/
    @FXML
    private TextField addProdID;
    /** product name text*/
    @FXML
    private TextField addProdName;
    /** product inventory text*/
    @FXML
    private TextField addProdInv;
    /** product cost text*/
    @FXML
    private TextField addProdCost;
    /** product max text*/
    @FXML
    private TextField addProdMax;
    /** product machine ID field*/
    @FXML
    private TextField addProdMachineID;
    /** product min text*/
    @FXML
    private TextField addProdMin;
    /** part ID col*/
    @FXML
    private TableColumn<Part, Integer> partIDCol;
    /** part name col*/
    @FXML
    private TableColumn<Part, String> partNameCol;
    /** part inventory col*/
    @FXML
    private TableColumn<Part, Integer> partInvLvlCol;
    /** part cost col*/
    @FXML
    private TableColumn<Part, Integer> partCostCol;
    /** assoc cost col*/
    @FXML
    private TableColumn<Part, Integer> assocIDCost;
    /** assoc name col*/
    @FXML
    private TableColumn<Part, String> assocPartName;
    /** assoc inventory col*/
    @FXML
    private TableColumn<Part, Integer> assocPartInv;
    /** assoc cost col*/
    @FXML
    private TableColumn<Part, Integer> assocPartCost;
    /** assoc table*/
    @FXML
    private TableView<Part> assocTable;
    /** part table*/
    @FXML
    private TableView<Part> partTable;
    /** cancel button*/
    private TextField partSearch;
    /** created new product*/
    private Product selectedProduct;
    /** search field for the product*/
    @FXML
    private TextField prodSearch;

    /** passing selected product to be modified
     *
     * populating form with selected product data
     * setting the parts columns with their respective attributes.
     * setting the products columns with their respective attributes.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedProduct = MenuController.getModifyProduct();

        /** populating form with selected product data*/
        addProdID.setText(String.valueOf(selectedProduct.getId()));
        addProdName.setText(selectedProduct.getName());
        addProdInv.setText(String.valueOf(selectedProduct.getStock()));
        addProdCost.setText(String.valueOf(selectedProduct.getPrice()));
        addProdMax.setText(String.valueOf(selectedProduct.getMax()));
        addProdMin.setText(String.valueOf(selectedProduct.getMin()));

        /** setting the parts columns with their respective attributes.*/
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInvLvlCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("price"));
        partTable.setItems(Inventory.getAllParts());
        /** setting the products columns with their respective attributes.*/
        assocIDCost.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        assocPartName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        assocPartInv.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        assocPartCost.setCellValueFactory(new PropertyValueFactory<Part, Integer>("price"));
        assocTable.setItems(selectedProduct.getAllAssociatedParts());

    }

    /** takes the gathered input field from user - mods product if no errors occurred
     * switches back to main form
     * @throws IOException*/
    @FXML
    public void onSave(ActionEvent e) throws IOException {

        try {
            Integer newID = total += 1;
            String name = addProdName.getText();
            Double cost = Double.parseDouble(addProdCost.getText());
            Integer inventory = Integer.parseInt(addProdInv.getText());
            Integer min = Integer.parseInt(addProdMin.getText());
            Integer max = Integer.parseInt(addProdMax.getText());

            /**checking errors - setting modified data*/
            if (name.isEmpty()) {
                displayAlert(5);
            } else if (min > max) {
                displayAlert(3);
            } else if (inventory > max || inventory < min) {
                displayAlert(4);
            } else {
                selectedProduct.setName(name);
                selectedProduct.setPrice(cost);
                selectedProduct.setMax(max);
                selectedProduct.setMin(min);
                selectedProduct.setStock(inventory);
            }
        }catch(Exception i){
            displayAlert(1);
        }
        // returns to main menu
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /** removes the associated part from the current product
     * if OK - remove associated part
     * throws an alert if the selected part is null*/
    @FXML
    void removeButtonAction(ActionEvent event) {

        Part selectedPart = assocTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(5); /**throws an alert if the selected part is null*/
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to remove the selected part?");
            Optional<ButtonType> result = alert.showAndWait();
            /**if OK - remove associated part*/
            if (result.isPresent() && result.get() == ButtonType.OK) {
                selectedProduct.deleteAssociatedPart(selectedPart);
                assocTable.setItems(selectedProduct.getAllAssociatedParts());
            }
        }
    }
    /** uses methods to look up specific parts according to the input ID(part) or String name
     * getting part name from search fields
     * searching for the string name first
     * try catch block to catch any errors if the string cannot be parsed into an integer
     *
     * partial fix - checking if null then add to list - new error. not populating all parts
     *
     * @param e */
    @FXML
    public void onSearch(ActionEvent e){
        ObservableList<Part> idPartList = FXCollections.observableArrayList();
        String partStr = prodSearch.getText(); // getting part name from search fields

        try{
            // searching for the string name first
            idPartList = Inventory.lookupPart(partStr);
            partTable.setItems(idPartList);
            Integer partId = Integer.parseInt(partStr);
            Part part = Inventory.lookupPart(partId);
            // try catch block to catch any errors if the string cannot be parsed into an integer
            if(part != null) {    // partial fix - checking if null then add to list - new error. not populating all parts
                idPartList.add(part);
                partTable.setItems(idPartList);
            }
        }catch(NumberFormatException i){
            // ignore
        }

        if(idPartList.size() == 0){
            displayAlert(2);
        }

        if(partStr.isEmpty() || partStr.isBlank())
        {
            partTable.setItems(Inventory.getAllParts());
        }
    }


    /** adds associated parts to a product
     * repopulating assoc table
     * */
    @FXML
    void addButtonAction(ActionEvent e) {

        Part selectedPart = partTable.getSelectionModel().getSelectedItem();

        /** checking if selected part exist - adding assoc part to product*/
        if (selectedPart == null) {
            displayAlert(5);
        } else {
            selectedProduct.addAssociatedPart(selectedPart);
            assocTable.setItems(selectedProduct.getAllAssociatedParts()); // repopulating assoc table
        }
    }
    /** sets an alert confirming to cancel and returns back to main menu
     * if OK return back to main screen
     * @throws IOException*/
    @FXML
    void cancelBtn(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();
        /**if OK return back to main screen*/
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
    /** alerts created to display specific errors
     *
     * @param alertType the number associated with an alert*/
    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Modifying Product");
                alert.setContentText("Form contains blank fields or invalid values.");
                alert.showAndWait();
                break;
            case 2:
                alertInfo.setTitle("Information");
                alertInfo.setHeaderText("Part not found");
                alertInfo.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be a number greater than 0 and less than Max.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Inventory");
                alert.setContentText("Inventory must be a number equal to or between Min and Max");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Part not selected");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("Error");
                alert.setHeaderText("Name Empty");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
                break;
        }
    }

}
