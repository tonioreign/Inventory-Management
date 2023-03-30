package com.inventory.inventorymanagement;

import com.inventory.inventorymanagement.Inventory;
import com.inventory.inventorymanagement.Part;
import com.inventory.inventorymanagement.Product;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
    // setting stage
    private Stage stage;
    // setting scene
    private Scene scene;
    // parent root variable
    private Parent root;
    // product list size
    private Integer total = Inventory.getAllProducts().size();
    // associated part list
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();
    // product id text
    @FXML
    private TextField addProdID;
    // product name text
    @FXML
    private TextField addProdName;
    // product inventory text
    @FXML
    private TextField addProdInv;
    // product cost text
    @FXML
    private TextField addProdCost;
    // product max text
    @FXML
    private TextField addProdMax;
    // product machine ID text
    @FXML
    private TextField addProdMachineID;
    // product min text
    @FXML
    private TextField addProdMin;
    // part ID col
    @FXML
    private TableColumn<Part, Integer> partIDCol;
    // part name col
    @FXML
    private TableColumn<Part, String> partNameCol;
    // part inventory col
    @FXML
    private TableColumn<Part, Integer> partInvLvlCol;
    // part cost col
    @FXML
    private TableColumn<Part, Integer> partCostCol;
    // assoc cost col
    @FXML
    private TableColumn<Product, Integer> assocIDCost;
    // assoc name col
    @FXML
    private TableColumn<Product, String> assocPartName;
    // assoc inventory col
    @FXML
    private TableColumn<Product, Integer> assocPartInv;
    // assoc cost col
    @FXML
    private TableColumn<Product, Integer> assocPartCost;
    // assoc table
    @FXML
    private TableView<Part> assocTable;
    // part table
    @FXML
    private TableView<Part> partTable;
    // part search text field
    @FXML
    private TextField prodSearch;

    @FXML TextField partSearch;
    // cancel button
    @FXML
    private Button cancelBtnProd;
    // save button
    private Button prodSave;

    // takes the gathered input field from user - creates new product if no errors occurred
    // switches back to main form
    @FXML
    public void onSave(ActionEvent e) throws IOException {
        Integer newID = total += 1;
        String name = addProdName.getText();
        Double cost = Double.parseDouble(addProdCost.getText());
        Integer inventory = Integer.parseInt(addProdInv.getText());
        Integer min = Integer.parseInt(addProdMin.getText());
        Integer max = Integer.parseInt(addProdMax.getText());

        if(name.isEmpty()){
            displayAlert(7);
        }
        else if(min > max)
        {
            displayAlert(3);
        }else if(inventory > max || inventory < min){
            displayAlert(4);
        }
        else{
            Inventory.addProduct(new Product(newID, name, cost, inventory, min, max));
        }


        // switches back to main menu
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // uses methods to look up specific parts according to the input ID(part) or String name
    @FXML
    public void onSearch(ActionEvent e){
        ObservableList<Part> idPartList = FXCollections.observableArrayList();
        String partStr = prodSearch.getText(); // getting part name from search fields

        // searching for the string name first
        partTable.setItems(Inventory.lookupPart(partStr));
        // try catch block to catch any errors if the string cannot be parsed into an integer
        try{
            Integer partId = Integer.parseInt(partStr);
            idPartList.add(Inventory.lookupPart(partId));
            partTable.setItems(idPartList);
        }catch(NumberFormatException i){
            // ignore
        }
    }

    // adds associated parts to a product
    /* still need to figure out how to add selected part to a newly created product */
    @FXML
    void addButtonAction(ActionEvent event) {

        Part selectedPart = partTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(5);
        } else {
            assocParts.add(selectedPart);
            assocTable.setItems(assocParts);
        }
    }

    // removes the associated part from the current product
    @FXML
    void removeButtonAction(ActionEvent event) {

        Part selectedPart = partTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(5); // throws an alert if the selected part is null
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to remove the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                assocParts.remove(selectedPart);
                assocTable.setItems(assocParts);
            }
        }
    }
    // sets an alert confirming to cancel and returns back to main menu
    @FXML
    void cancelBtn(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        // if OK return back to main screen
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
    // alerts created to display specific errors
    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Product");
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
    // initializes auto generated ID for product
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Integer newTotal = total + 1;
        addProdID.setText(newTotal.toString());

        // setting the parts columns with their respective attributes.
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInvLvlCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("price"));
        partTable.setItems((Inventory.getAllParts()));
        // setting the products columns with their respective attributes.
        assocIDCost.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        assocPartName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        assocPartInv.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        assocPartCost.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
    }
}

