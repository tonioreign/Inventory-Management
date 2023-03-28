package com.inventory.inventorymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {

    private Stage stage;
    private Scene scene;
    private Parent root;
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
    void cancelBtn(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
