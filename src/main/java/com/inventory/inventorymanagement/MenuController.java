package com.inventory.inventorymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML

    public TableView partTable;
    public TableColumn partIDCol;
    public TableColumn partNameCol;
    public TableColumn partInvLvlCol;
    public TableColumn partCostCol;
    public Button addPart;
    public Button modPart;
    public Button deletePart;
    public TableView productTable;
    public TableColumn productIDCol;
    public TableColumn productNameCol;
    public TableColumn productInvLvlCol;
    public TableColumn productCostCol;
    public Button addProduct;
    public Button modProduct;
    public Button deleteProduct;
    public TextField searchPart;
    public TextField searchProduct;
    public Button exit;

    private Stage stage;
    private Scene scene;
    private Parent root;

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

    public void onExit(ActionEvent e) {
        System.out.println("Exit");
    }

    public void cancelBtn(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
