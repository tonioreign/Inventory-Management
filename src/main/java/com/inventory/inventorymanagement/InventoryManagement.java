package com.inventory.inventorymanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryManagement extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try{
            Parent menu = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(menu);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}