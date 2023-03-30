package com.inventory.inventorymanagement;

import com.inventory.inventorymanagement.InHouse;
import com.inventory.inventorymanagement.Inventory;
import com.inventory.inventorymanagement.Outsourced;
import com.inventory.inventorymanagement.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    // settings the stage
    private Stage stage;
    // setting the scene
    private Scene scene;
    private Parent root;
    // getting total inventory for parts
    private Integer total = Inventory.getAllParts().size();
    // outsourced radio button
    @FXML
    private RadioButton addPartOutSourced;
    // in-house radio button
    @FXML
    private RadioButton addPartInHouse;
    // part ID text field
    @FXML
    private TextField addPartID;
    // part name text field
    @FXML
    private TextField addPartName;
    // part inventory level field
    @FXML
    private TextField addPartInv;
    // part cost field
    @FXML
    private TextField addPartCost;
    // part max field
    @FXML
    private TextField addPartMax;
    // part machine ID field
    @FXML
    private TextField addPartMachine;
    // part min field
    @FXML
    private TextField addPartMin;
    // part save button
    @FXML
    private Button addPartSave;
    // part cancel button
    @FXML
    private Button cancelBtnPart;
    // part machine ID label
    @FXML
    private Label machineIdField;

    // setting field level to Company when In-House is selected
    @FXML
    public void onInHouse(ActionEvent e) {
        machineIdField.setText("Machine ID");
        addPartOutSourced.setSelected(false);
    }

    // setting field level to Machine ID when OutSourced is selected
    @FXML
    public void onOutsourced(ActionEvent e) {
        machineIdField.setText("Outsourced");
        addPartInHouse.setSelected(false);
    }

    // takes the gathered input field from user - creates new part if no errors occurred
    // switches back to main form
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
                }

            }
        } catch (NumberFormatException i) {
            displayAlert(1);
        }
        /*seen a test that you should be able to save without machine ID field? displaying an error to show that it's needed
        * according to the given class*/

        // returns back to main menu
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    // sets an alert confirming to cancel and returns back to main menu
    @FXML
    void cancelBtn(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        // confirming OK to return back to main menu (cancel)
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
                alert.setContentText("Machine ID may only contain numbers and cannot be empty.");
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
    // initializes generated ID and sets text for populated ID for part form
    // sets in-house to "selected" automatically on "AddParts" form
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Integer newTotal = total + 1;
        addPartID.setText(newTotal.toString());
        addPartInHouse.setSelected(true);
    }
}
