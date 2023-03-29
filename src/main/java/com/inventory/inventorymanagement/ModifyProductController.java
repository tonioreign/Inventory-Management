package com.inventory.inventorymanagement;

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

public class ModifyProductController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Integer total = Inventory.getAllProducts().size();
    @FXML
    private TextField addProdID;

    @FXML
    private TextField addProdName;

    @FXML
    private TextField addProdInv;

    @FXML
    private TextField addProdCost;

    @FXML
    private TextField addProdMax;

    @FXML
    private TextField addProdMachineID;

    @FXML
    private TextField addProdMin;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvLvlCol;

    @FXML
    private TableColumn<Part, Integer> partCostCol;

    @FXML
    private TableColumn<Product, Integer> productIDCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvLvlCol;

    @FXML
    private TableColumn<Product, Integer> productCostCol;
    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableView<Part> partTable;

    @FXML
    private Button cancelBtnProd;

    private Product selectedProduct;
    private Button prodSave;

    public void onSave(ActionEvent e) throws IOException {
        Integer newID = total += 1;
        String name = addProdName.getText();
        Double cost = Double.parseDouble(addProdCost.getText());
        Integer inventory = Integer.parseInt(addProdInv.getText());
        Integer min = Integer.parseInt(addProdMin.getText());
        Integer max = Integer.parseInt(addProdMax.getText());

        Inventory.addProduct(new Product(newID, name, cost, inventory, min, max));

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
        selectedProduct = MenuController.getModifyProduct();


        addProdID.setText(String.valueOf(selectedProduct.getId()));
        addProdName.setText(selectedProduct.getName());
        addProdInv.setText(String.valueOf(selectedProduct.getStock()));
        addProdCost.setText(String.valueOf(selectedProduct.getPrice()));
        addProdMax.setText(String.valueOf(selectedProduct.getMax()));
        addProdMin.setText(String.valueOf(selectedProduct.getMin()));

        addProdID.setText(total.toString());
        partTable.setItems(Inventory.getAllParts());
        productTable.setItems((Inventory.getAllProducts()));
        // setting the parts columns with their respective attributes.
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInvLvlCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("price"));
        // setting the products columns with their respective attributes.
        productIDCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInvLvlCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productCostCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));


    }
}
