package com.inventory.inventorymanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        /*
            Ran into an error here stating that this.allParts table was null when the table was filled. Learned that I can't
            have the same controller referenced to multiple fxml files. RUNTIME ERROR
        */
        Inventory.addPart(new Outsourced(1, "Brake", 9.99, 2, 0, 5, "Reign"));
        Inventory.addPart(new Outsourced(2, "Latter", 9.99, 2, 0, 5, "Reign"));
        Inventory.addPart(new Outsourced(3, "Shovel", 9.99, 2, 0, 5, "Reign"));
        Inventory.addPart(new Outsourced(4, "Brakes", 9.99, 2, 0, 5, "Reign"));
        Inventory.addPart(new Outsourced(5, "ScrewDriver", 9.99, 2, 0, 5, "Reign"));
        Inventory.addPart(new Outsourced(6, "Latex", 9.99, 2, 0, 5, "Reign"));
        // setting each table on the menu screen with its individual category.
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
        try {
            Inventory.deletePart(partTable.getSelectionModel().getSelectedItem()); // deletes the selected part
        }catch(Exception ex){
            ex.printStackTrace();
        }
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
        try {
            Inventory.deleteProduct(productTable.getSelectionModel().getSelectedItem()); // deletes the selected product
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void onSearch(ActionEvent e){
        ObservableList<Part> idPartList = FXCollections.observableArrayList();
        ObservableList<Product> idProdList = FXCollections.observableArrayList();

        String partStr = searchPart.getText(); // getting part name from search fields
        String prodStr = searchProduct.getText();

        // searching for the string name first
        partTable.setItems(Inventory.lookupPart(partStr));
        productTable.setItems(Inventory.lookupProduct(prodStr));
        // try catch block to catch any errors if the string cannot be parsed into an integer
        try{
            Integer partId = Integer.parseInt(partStr);
            idPartList.add(Inventory.lookupPart(partId));
            partTable.setItems(idPartList);

            Integer prodId = Integer.parseInt(prodStr);
            idProdList.add(Inventory.lookupProduct(prodId));
            productTable.setItems(idProdList);
        }catch(NumberFormatException i){
            // ignore
        }
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
