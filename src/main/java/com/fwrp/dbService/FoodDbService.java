/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.FoodDAO;
import com.fwrp.dataaccess.dao.FoodDAOImpl;
import com.fwrp.dataaccess.dao.InventoryDAO;
import com.fwrp.dataaccess.dao.InventoryDAOImpl;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.models.Food;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author robin
 */
public class FoodDbService {
    private FoodDAO foodDAO = null;
    private InventoryDAO inventoryDAO = null;
    
    /**
     * Constructs a DatabaseOperation object and initializes DAO objects.
     */
    public FoodDbService(){
        foodDAO = new FoodDAOImpl(); 
        inventoryDAO = new InventoryDAOImpl(); 
    }
    
    public boolean AddFood(Food food) throws DataAlreadyExistsException, DataInsertionFailedException, Exception {      
        Connection conn = null;
        
        try{
            conn = DataSource.getInstance().getConnection();
            //start transaction
            conn.setAutoCommit(false); 
            
            Food foodExist = foodDAO.getFoodByName(food.getName(), conn);
            
            if(foodExist != null){
                throw new DataAlreadyExistsException("Food " + food.getName() + " aleady exists.");
            }
            
            boolean isFoodAdded = foodDAO.addFood(food, conn); 
            if(!isFoodAdded){
                throw new DataInsertionFailedException("Failed to add food with name " + food.getName() + ".");
            } 
            
            Food storedFood = foodDAO.getFoodByName(food.getName(), conn);            
            boolean isInventoryAdded = inventoryDAO.addInventoryByFoodId(storedFood.getId(), conn);
            if(!isInventoryAdded){
                throw new DataInsertionFailedException("Failed to add inventory for food with name " + food.getName() + ".");
            }
            
            conn.commit(); // 提交事务
        } catch(SQLException e){
            if (conn != null) {
                conn.rollback(); // 回滚事务
            }   
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // 恢复自动提交
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    e.printStackTrace(); // 如果关闭连接失败，记录异常
                }
            }
        }
        
        System.out.println("new food stored");
        return true;
    }
    
    public Food getFoodByName(String name)throws SQLException, ClassNotFoundException {
        Connection conn = DataSource.getInstance().getConnection();
        Food food = null;
        
        try{
            food = foodDAO.getFoodByName(name, conn);
        } catch(SQLException e){
            throw e;
        } finally{
            if (conn != null) {
                try {
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    e.printStackTrace(); // Log the exception if closing connection fails
                }
            }
        }
        
        return food;
    }
    
    public Food getFoodById(int foodId)throws SQLException, ClassNotFoundException {
        Connection conn = DataSource.getInstance().getConnection();
        Food food = null;
        
        try{
            food = foodDAO.getFoodById(foodId, conn);
        } catch(SQLException e){
            throw e;
        } finally{
            if (conn != null) {
                try {
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    e.printStackTrace(); // Log the exception if closing connection fails
                }
            }
        }
        
        return food;
    }
    
    public ArrayList<Food> getAllFoods() throws ClassNotFoundException, SQLException{
        Connection conn = DataSource.getInstance().getConnection();
        ArrayList<Food> foods = new ArrayList<>();
        try{
            foods = foodDAO.getAllFoods(conn);
        } finally{
            if (conn != null) {               
                conn.close(); 
            }
        }        
        return foods;
    }
    
    public boolean updateFood(Food food) throws DataInsertionFailedException, SQLException, ClassNotFoundException{
        Connection conn = null;
        
        try{
            conn = DataSource.getInstance().getConnection();
            
            boolean isFoodUpdated = foodDAO.updateFood(food, conn); 
            if(!isFoodUpdated){
                throw new DataInsertionFailedException("Failed to update food with name " + food.getName() + ".");
            } 
        } catch(SQLException e){            
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    e.printStackTrace(); // 如果关闭连接失败，记录异常
                }
            }
        }
        
        return true;
    }
}
