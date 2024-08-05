/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.InventoryDTO;
import com.fwrp.models.Food;
import com.fwrp.models.Inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * This class implements the {@link InventoryDAO} interface to provide a concrete implementation.
 * It provides methods to add, retrieve, update, and remove inventory data from a data source.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class InventoryDAOImpl implements InventoryDAO {

    /**
     * Adds a new inventory entry for a specific food item to the data source.
     * 
     * @param foodId The ID of the food item for which inventory is being added.
     * @param conn SQL connection
     * @return boolean Returns true if the inventory was successfully added, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean addInventoryByFoodId(int foodId, Connection conn)  throws SQLException{
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement("INSERT INTO inventory (food_id, "
                + "quantity_normal, quantity_discount, quantity_donation) "
                + "VALUES (?,0,0,0)")) {

            pstmt.setInt(1, foodId);
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        }

        return isSuccess;
    }

    /**
     * Retrieves inventory information for a specific food item by its ID.
     * 
     * @param foodId The ID of the food item to retrieve inventory for.
     * @param conn SQL connection
     * @return InventoryDTO Returns the {@link InventoryDTO} object if found, null otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public InventoryDTO getInventoryByFoodId(int foodId, Connection conn)  throws SQLException{
        InventoryDTO inventoryDTO  = null;

        try(PreparedStatement pstmt = conn.prepareStatement(
                "SELECT * FROM inventory WHERE food_id = ?"
        )) {

            pstmt.setInt(1, foodId);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    inventoryDTO = new InventoryDTO(
                            rs.getInt("id"),
                            rs.getInt("food_id"),
                            rs.getInt("quantity_normal"),
                            rs.getInt("quantity_discount"),
                            rs.getInt("quantity_donation")
                    );
                }
            }
        }

        return inventoryDTO;
    }

    /**
     * Updates the inventory data for a specific food item.
     * 
     * @param inventoryDTO The {@link InventoryDTO} object containing updated inventory data.
     * @param conn SQL connection
     * @return boolean Returns true if the inventory was successfully updated, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean updateInventory(InventoryDTO inventoryDTO, Connection conn)  throws SQLException{
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE inventory SET id = ?, food_id = ?, "
                        + "quantity_normal = ?, quantity_discount = ?, "
                        + "quantity_donation = ? "
                        + "WHERE id = ?")) {

            pstmt.setInt(1, inventoryDTO.getId());
            pstmt.setInt(2, inventoryDTO.getFoodId());
            pstmt.setInt(3, inventoryDTO.getQtyNormal());
            pstmt.setInt(4, inventoryDTO.getQtyDiscount());
            pstmt.setInt(5, inventoryDTO.getQtydonation());
            pstmt.setInt(6, inventoryDTO.getId());

            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        }

        return isSuccess;
    }

    /**
     * Removes the inventory entry for a specific food item from the data source.
     * 
     * @param inventoryDTO The {@link InventoryDTO} object representing the inventory to be removed.
     * @param conn SQL connection
     * @return boolean Returns true if the inventory was successfully removed, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    public boolean removeInventory(InventoryDTO inventoryDTO, Connection conn)  throws SQLException{
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM inventory WHERE id = ?")) {

            pstmt.setInt(1, inventoryDTO.getId());
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        }

        return isSuccess;
    }

    /**
     * Retrieves a list of all donation inventories.
     * 
     * @param conn SQL connection
     * @return List&lt;Inventory&gt; A list of {@link Inventory} objects representing donation inventories.
     * @throws SQLException if a database access error occurs
     */
    public List<Inventory> getDonationInventories(Connection conn) throws SQLException {
        Inventory inventory  = null;
        Food food=null;
        ArrayList<Inventory> donationInventories = new ArrayList<Inventory>();
        try(PreparedStatement pstmt = conn.prepareStatement(
                "select a.id,b.id as food_id,b.name as food_name,b.expire_days,b.unitprice,b.discount,a.quantity_normal,a.quantity_discount,a.quantity_donation from inventory a,foods b where a.food_id=b.id and a.quantity_donation>0"
        )) {

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    food=new Food(rs.getInt("food_id"),rs.getString("food_name"),rs.getInt("expire_days"),rs.getDouble("unitprice"),rs.getDouble("discount"));
                    inventory = new Inventory(
                            rs.getInt("id"),
                            food,
                            rs.getInt("quantity_normal"),
                            rs.getInt("quantity_discount"),
                            rs.getInt("quantity_donation")
                    );
                    donationInventories.add(inventory);
                }
            }
        }

        return donationInventories;
    }
    
	/**
	 * Retrieves a list of all discount inventories.
	 * 
	 * @param conn SQL connection
	 * @return List&lt;Inventory&gt; A list of {@link Inventory} objects representing discount inventories.
	 * @throws SQLException if a database access error occurs
	 */
    public List<Inventory> getDiscountInventories(Connection conn) throws SQLException {
        Inventory inventory  = null;
        Food food=null;
        ArrayList<Inventory> discountInventories = new ArrayList<Inventory>();
        try(PreparedStatement pstmt = conn.prepareStatement(
                "select a.id,b.id as food_id,b.name as food_name,b.expire_days,b.unitprice,b.discount,a.quantity_normal,a.quantity_discount,a.quantity_donation from inventory a,foods b where a.food_id=b.id and a.quantity_discount>0"
        )) {

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    food=new Food(rs.getInt("food_id"),rs.getString("food_name"),rs.getInt("expire_days"),rs.getDouble("unitprice"),rs.getDouble("discount"));
                    inventory = new Inventory(
                            rs.getInt("id"),
                            food,
                            rs.getInt("quantity_normal"),
                            rs.getInt("quantity_discount"),
                            rs.getInt("quantity_donation")
                    );
                    discountInventories.add(inventory);
                }
            }
        }

        return discountInventories;
    }

    /**
     * Retrieves all inventory data, including quantities for each item.
     * 
     * @param conn SQL connection
     * @return HashMap<Integer, Integer[]> A map where the key is the food ID and the value is an array containing surplus quantity and inventory quantities.
     * @throws SQLException if a database access error occurs
     */
    public HashMap<Integer, Integer[]> getAllInventoryData(Connection conn) throws SQLException{
        HashMap<Integer, Integer[]> foodSurplusMap = new HashMap<>();

        try(PreparedStatement pstmt = conn.prepareStatement(
                "SELECT "
                        + "inventory.food_id, "
                        + "COALESCE(SUM(expire_infos.quantity), 0) AS surplus_qty,"
                        + "inventory.quantity_normal AS qty_normal,"
                        + "inventory.quantity_discount AS qty_discount,"
                        + "inventory.quantity_donation AS qty_donation "
                        + "FROM "
                        + "inventory "
                        + "LEFT JOIN expire_infos "
                        + "ON inventory.food_id = expire_infos.food_id "
                        + "GROUP BY "
                        + "inventory.food_id, "
                        + "inventory.quantity_normal,"
                        + "inventory.quantity_discount, "
                        + "inventory.quantity_donation")){

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    int foodId = rs.getInt("food_id");
                    int surplusQty = rs.getInt("surplus_qty");
                    int qtyNormal = rs.getInt("qty_normal");
                    int qtyDiscount = rs.getInt("qty_discount");
                    int qtyDonation = rs.getInt("qty_donation");

                    foodSurplusMap.put(foodId, new Integer[]{surplusQty, qtyNormal, qtyDiscount, qtyDonation});
                }
            }
        }

        return foodSurplusMap;
    }

}
