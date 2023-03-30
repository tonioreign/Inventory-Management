package com.inventory.inventorymanagement.model;

/**
 * InHouse for part
 *
 * @author Antonio
 * */
public class InHouse extends Part {
    /** machine ID for part */
    private int machineId;

    /** Constructor for a new instance of an InHouse object
     *
     * @param id the ID for part
     * @param name  the name for part
     * @param price the price for part
     * @param stock the inventory level for part
     * @param min the min for part
     * @param max the maximum for part
     * @param machineId the machine ID for part
     * */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    /** the setter for machine ID*/
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }
    /** the getter for machine ID*/
    public int getMachineId(){
        return machineId;
    }

}
