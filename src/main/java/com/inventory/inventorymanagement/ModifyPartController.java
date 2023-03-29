package com.inventory.inventorymanagement;

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

public class ModifyPartController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Integer total = Inventory.getAllProducts().size();
    @FXML
    private RadioButton addPartOutSourced;

    @FXML
    private RadioButton addPartInHouse;

    @FXML
    private TextField addPartID;

    @FXML
    private TextField addPartName;

    @FXML
    private TextField addPartInv;

    @FXML
    private TextField addPartCost;

    @FXML
    private TextField addPartMax;

    @FXML
    private TextField addPartMachine;

    @FXML
    private TextField addPartMin;

    @FXML
    private Button addPartSave;

    @FXML
    private Button cancelBtnPart;

    @FXML
    private Label machineIdField;

    private Part selectedPart;



    public void onInHouse(ActionEvent e){
        machineIdField.setText("Company");
        addPartOutSourced.setSelected(false);
    }

    public void onOutsourced(ActionEvent e){
        machineIdField.setText("Machine ID");
        addPartInHouse.setSelected(false);
    }

    public void onSave(ActionEvent e) throws IOException {
        Integer newID = total += 1;
        String name = addPartName.getText();
        Double cost = Double.parseDouble(addPartCost.getText());
        Integer inventory = Integer.parseInt(addPartInv.getText());
        Integer min = Integer.parseInt(addPartMin.getText());
        Integer max = Integer.parseInt(addPartMax.getText());
        Integer machineID = Integer.parseInt(addPartMachine.getText());
        String companyName = addPartMachine.getText().toString();

        if(addPartOutSourced.isSelected()){
            Inventory.addPart(new Outsourced(newID, name, cost, inventory, min, max, companyName));
        }else if(addPartInHouse.isSelected()){
            Inventory.addPart(new InHouse(newID, name, cost, inventory, min, max, machineID));
        }else{
            System.out.println("Please select an option - InHouse or OutSourced");
        }

        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void cancelBtn(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addPartID.setText(total.toString());
        addPartInHouse.setSelected(true);

        selectedPart = MenuController.getModifyPart();

        if (selectedPart instanceof InHouse) {
            addPartInHouse.setSelected(true);
            machineIdField.setText("Machine ID");
            addPartMachine.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }

        if (selectedPart instanceof Outsourced){
            addPartOutSourced.setSelected(true);
            machineIdField.setText("Company Name");
            machineIdField.setText(((Outsourced) selectedPart).getCompanyName());
        }

        addPartID.setText(String.valueOf(selectedPart.getId()));
        addPartName.setText(selectedPart.getName());
        addPartInv.setText(String.valueOf(selectedPart.getStock()));
        addPartCost.setText(String.valueOf(selectedPart.getPrice()));
        addPartMax.setText(String.valueOf(selectedPart.getMax()));
        addPartMin.setText(String.valueOf(selectedPart.getMin()));
    }
}
