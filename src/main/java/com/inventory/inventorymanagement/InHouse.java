package com.inventory.inventorymanagement;

public class InHouse extends Part {
    // InHouse machine ID
    private int machineId;

    // InHouse constructor
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    // set machine ID
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }
    // get machine ID
    public int getMachineId(){
        return machineId;
    }

}
