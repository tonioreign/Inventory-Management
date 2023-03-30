package com.inventory.inventorymanagement.controller;

import com.inventory.inventorymanagement.model.InHouse;
import com.inventory.inventorymanagement.model.Inventory;
import com.inventory.inventorymanagement.model.Outsourced;
import com.inventory.inventorymanagement.model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * Controller class that provides control logic for the modify part screen of the application.
 *
 * @author Antonio Jenkins
 */

public class ModifyPartController implements Initializable {
    /** set stage*/
    private Stage stage;
    /** set scene*/
    private Scene scene;
    /** et parent*/
    private Parent root;
    /** create total variable for product-list size*/
    private Integer total = Inventory.getAllProducts().size();
    /** outsourced radiobutton*/
    @FXML
    private RadioButton addPartOutSourced;
    /** in-house radiobutton*/
    @FXML
    private RadioButton addPartInHouse;
    /** part ID text*/
    @FXML
    private TextField addPartID;
    /** part name text*/
    @FXML
    private TextField addPartName;
    /** part inventory text*/
    @FXML
    private TextField addPartInv;
    /** part cost text*/
    @FXML
    private TextField addPartCost;
    /** part max text*/
    @FXML
    private TextField addPartMax;
    /** part machine ID text*/
    @FXML
    private TextField addPartMachine;
    /** part min text*/
    @FXML
    private TextField addPartMin;
    /** part save button*/
    @FXML
    private Button addPartSave;
    /** part cancel button*/
    @FXML
    private Button cancelBtnPart;
    /** machine ID label*/
    @FXML
    private Label machineIdField;
    /** created new "Part"*/
    private Part selectedPart;
    /** setting field level to Company when In-House is selected*/
    @FXML
    public void onInHouse(ActionEvent e){
        machineIdField.setText("Machine ID");
        addPartOutSourced.setSelected(false);
    }
    /** setting field level to Machine ID when OutSourced is selected*/
    @FXML
    public void onOutsourced(ActionEvent e){
        machineIdField.setText("Outsourced");
        addPartInHouse.setSelected(false);
    }
    /** takes the gathered input field from user - modifies current part if no errors occurred
     * once the new part is created - old part is deleted from inventory
     * switches back to main form
     *
     * checks if outsourced or inhouse is selected - check errors then creates outsourced part
     * creating new part
     * deleting old part from inventory
     * returns back to main menu
     *
     * seen a test that you should be able to save without machine ID field? displaying an error to show that it's needed
     * according to the given class
     * @throws IOException*/
    @FXML
    public void onSave(ActionEvent e) throws IOException {
        Integer newID = total += 1;
        try {
            String name = addPartName.getText();
            Double cost = Double.parseDouble(addPartCost.getText());
            Integer inventory = Integer.parseInt(addPartInv.getText());
            Integer min = Integer.parseInt(addPartMin.getText());
            Integer max = Integer.parseInt(addPartMax.getText());
            Integer machineID = null;
            String companyName = addPartMachine.getText().toString();

            if (addPartOutSourced.isSelected()) { // checks if outsourced is selected - check errors then creates outsourced part
                if (name.isEmpty()) {
                    displayAlert(5);
                } else if (min > max) {
                    displayAlert(3);
                } else if (inventory > max || inventory < min) {
                    displayAlert(4);
                } else {
                    Inventory.addPart(new Outsourced(newID, name, cost, inventory, min, max, companyName));
                    Inventory.deletePart(selectedPart); // deleting old part from inventory
                }
            } else if (addPartInHouse.isSelected()) {  // checks if in-house is selected - check errors then creates in-house part
                try{
                    machineID = Integer.parseInt(addPartMachine.getText());
                }catch(Exception n){
                    displayAlert(2);
                }
                if (name.isEmpty()) {
                    displayAlert(5);
                } else if (min > max) {
                    displayAlert(3);
                } else if (inventory > max || inventory < min) {
                    displayAlert(4);
                } else if(addPartMachine.getText().isEmpty()){
                    displayAlert(2);
                }else {
                    Inventory.addPart(new InHouse(newID, name, cost, inventory, min, max, machineID)); // adding part into inventory
                    Inventory.deletePart(selectedPart); // deleting old part from inventory
                }

            }
        } catch (NumberFormatException i) {
            displayAlert(1);
        }

        // returns back to main menu
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /** sets an alert confirming to cancel and returns back to main menu
     * @throws IOException*/
    @FXML
    void cancelBtn(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

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

            switch (alertType) {
                case 1:
                    alert.setTitle("Error");
                    alert.setHeaderText("Error Adding Part");
                    alert.setContentText("Form contains blank fields or invalid values.");
                    alert.showAndWait();
                    break;
                case 2:
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid value for Machine ID");
                    alert.setContentText("Machine ID may only contain numbers.");
                    alert.showAndWait();
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
                    alert.setContentText("Inventory must be a number equal to or between Min and Max.");
                    alert.showAndWait();
                    break;
                case 5:
                    alert.setTitle("Error");
                    alert.setHeaderText("Name Empty");
                    alert.setContentText("Name cannot be empty.");
                    alert.showAndWait();
                    break;
            }
        }

    /** passing modified part - getting part data to be modified
     * checking if it's an instance of InHouse
     * checking if it's an instance of Outsourced
     * checking if it's an instance of Outsourced
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addPartID.setText(total.toString());

        selectedPart = MenuController.getModifyPart();
        /** checking if it's an instance of InHouse*/
        if (selectedPart instanceof InHouse) {
            addPartInHouse.setSelected(true);
            machineIdField.setText("Machine ID");
            addPartMachine.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }
        /** checking if it's an instance of Outsourced*/
        if (selectedPart instanceof Outsourced){
            addPartOutSourced.setSelected(true);
            machineIdField.setText("Outsourced");
            addPartMachine.setText(String.valueOf(((Outsourced) selectedPart).getCompanyName()));
        }
        /** checking if it's an instance of Outsourced*/
        addPartID.setText(String.valueOf(selectedPart.getId()));
        addPartName.setText(String.valueOf(selectedPart.getName()));
        addPartInv.setText(String.valueOf(selectedPart.getStock()));
        addPartCost.setText(String.valueOf(selectedPart.getPrice()));
        addPartMax.setText(String.valueOf(selectedPart.getMax()));
        addPartMin.setText(String.valueOf(selectedPart.getMin()));
    }
}
