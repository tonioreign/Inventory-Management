module com.inventory.inventorymanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.inventory.inventorymanagement to javafx.fxml;
    exports com.inventory.inventorymanagement;
}