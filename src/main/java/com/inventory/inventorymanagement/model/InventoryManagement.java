package com.inventory.inventorymanagement.model;

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
            Parent menu = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
            Scene scene = new Scene(menu);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /** CREATING PARTS*/
        Part brakes = new Outsourced(1, "brakes", 9.99, 1, 1, 10, "Reign");
        Part tires = new InHouse(2, "tires", 9.99, 1, 1, 10, 304);

        /** ADDING PARTS INTO INVENTORY*/
        Inventory.addPart(brakes);
        Inventory.addPart(tires);

        /** CREATING PRODUCTS*/
        Product bikes = new Product(1,"bike", 99.99, 1, 1, 5);
        Product goCarts = new Product(2,"go-carts", 199.99, 1, 1, 5);

        /** ADDING ASSOCIATED PARTS*/
        goCarts.addAssociatedPart(tires);

        /** ADDING PRODUCTS INTO INVENTORY*/
        Inventory.addProduct(bikes);
        Inventory.addProduct(goCarts);


        launch();


        /**
        * "RUNTIME ERROR" Can't figure out how to refactor the code - everytime I try multiple ways that would work with a non maven build
        * I get runtime errors "Location required" won't let me move files into new created folders to make it more organized
        *
        * FIXXXX - deleted "com" folder and moved fxml files into a specific folder then rerouted all controllers and models
        * to there respective fxml files.
        * */
    }
}