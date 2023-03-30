module com.inventory.inventorymanagement {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.inventory.inventorymanagement.controller;
    opens com.inventory.inventorymanagement.controller to javafx.fxml;
    exports com.inventory.inventorymanagement.model;
    opens com.inventory.inventorymanagement.model to javafx.fxml;
}