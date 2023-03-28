package com.inventory.inventorymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private TableView<Part> partTable;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvLvlCol;

    @FXML
    private TableColumn<Part, Integer> partCostCol;

    @FXML
    private Button addPart;

    @FXML
    private Button modPart;

    @FXML
    private Button deletePart;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> productIDCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvLvlCol;

    @FXML
    private TableColumn<Product, Integer> productCostCol;

    @FXML
    private Button addProduct;

    @FXML
    private Button modProduct;

    @FXML
    private Button deleteProduct;

    @FXML
    private TextField searchPart;

    @FXML
    private TextField searchProduct;

    @FXML
    private Button exit;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // creating a part & product to testt the tableview
        Product prod = new Product(1, "Brake", 19.99, 10, 1, 5);
        Part part = new Outsourced(2, "Brake", 19.99, 10, 1, 5, "TonioReign");
        Inventory.addProduct(prod);
        Inventory.addPart(part);
        // setting each table on the menu screen with
        // its individual category.
        partTable.setItems(Inventory.getAllParts());
        productTable.setItems((Inventory.getAllProducts()));

        // setting the parts columns with their respective attributes.
        partIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInvLvlCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("price"));
        // setting the products columns with their respective attributes.
        productIDCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInvLvlCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productCostCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
    }
    public void onAddPart(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onModPart(ActionEvent e)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void onDeletePart(ActionEvent e){

    }

    public void onAddProduct(ActionEvent e)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onModProduct(ActionEvent e)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void onDeleteProduct(ActionEvent e){
    }


    public void cancelBtn(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exitBtn(ActionEvent e){
        stage.close();
    }
}
