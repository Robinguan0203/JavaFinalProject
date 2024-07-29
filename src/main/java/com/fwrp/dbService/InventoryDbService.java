/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.constants.OtherConstants;
import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.ExpireInfoDAO;
import com.fwrp.dataaccess.dao.ExpireInfoDAOImpl;
import com.fwrp.dataaccess.dao.FoodDAO;
import com.fwrp.dataaccess.dao.FoodDAOImpl;
import com.fwrp.dataaccess.dao.InventoryDAO;
import com.fwrp.dataaccess.dao.InventoryDAOImpl;
import com.fwrp.dataaccess.dao.OrderDAO;
import com.fwrp.dataaccess.dao.TransactionDAO;
import com.fwrp.dataaccess.dao.TransactionDAOImpl;
import com.fwrp.dataaccess.dao.UserDAO;
import com.fwrp.dataaccess.dao.UserDAOImpl;
import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dataaccess.dto.InventoryDTO;
import com.fwrp.dataaccess.dto.TransactionDTO;
import com.fwrp.dataaccess.dto.UserDTO;
import com.fwrp.exceptions.NegativeInventoryException;
import com.fwrp.models.ExpireInfo;
import com.fwrp.models.Food;
import com.fwrp.models.Inventory;
import com.fwrp.models.Transaction;
import com.fwrp.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author robin
 */
public class InventoryDbService {
    private InventoryDAO inventoryDAO = null;
    private TransactionDAO transactionDAO = null;
    private ExpireInfoDAO expireInfoDAO = null;
    private FoodDAO foodDAO = null;
    private UserDAO userDAO = null;
    //private OrderDAO orderDAO = null;



    public InventoryDbService(){
        inventoryDAO = new InventoryDAOImpl();
        transactionDAO = new TransactionDAOImpl();
        expireInfoDAO = new ExpireInfoDAOImpl();
        foodDAO = new FoodDAOImpl();
        userDAO = new UserDAOImpl();
        //orderDAO = new OrderDAOImple();
    }

    public boolean addTransaction(TransactionDTO transactionDTO) throws NegativeInventoryException, SQLException, ClassNotFoundException{
        Connection conn = null;
        InventoryDTO inventoryDTO = null;
        int qtyNormal;
        int qtyDiscount;
        int qtyDonation;

        try{
            conn = DataSource.getInstance().getConnection();
            //start transaction
            conn.setAutoCommit(false);

            //query inventory
            inventoryDTO = inventoryDAO.getInventoryByFoodId(transactionDTO.getFoodId(), conn);

            qtyNormal = inventoryDTO.getQtyNormal() + transactionDTO.getQtyNormal();
            qtyDiscount = inventoryDTO.getQtyDiscount() + transactionDTO.getQtyDiscount();
            qtyDonation = inventoryDTO.getQtydonation() + transactionDTO.getQtyDonation();

            //validate inventory
            if(qtyNormal < 0 || qtyDiscount < 0 || qtyDonation < 0){
                throw new NegativeInventoryException("Inventory cannot be negative!");
            }

            //add transaction
            boolean isTransactionAdded = transactionDAO.addTransaction(transactionDTO, conn);
            if(!isTransactionAdded){
                throw new SQLException("Inser transaction fails.");
            }

            inventoryDTO.setQtyNormal(qtyNormal);
            inventoryDTO.setQtyDiscount(qtyDiscount);
            inventoryDTO.setQtydonation(qtyDonation);

            //update inventory
            boolean isInventoryUpdated = inventoryDAO.updateInventory(inventoryDTO, conn);

            if(!isInventoryUpdated){
                throw new SQLException("Inventory updation fails.");
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

        System.out.println("inventory updated");
        return true;
    }

    public boolean addExpireInfo(ExpireInfoDTO expireInfoDTO) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            boolean isExpireInfoAdded = expireInfoDAO.addExpireInfo(expireInfoDTO, conn);
            if(!isExpireInfoAdded){
                throw new SQLException("Inser food expire info fails.");
            }
        }catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }

        System.out.println("Food expire Info added");
        return true;
    }

    public ExpireInfo getExpireInfoById(int expireInfoId) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        ExpireInfoDTO expireInfoDTO = null;
        ExpireInfo expireInfo = null;

        try{
            conn = DataSource.getInstance().getConnection();
            expireInfoDTO = expireInfoDAO.getExpireInfoById(expireInfoId, conn);
        } catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }
        
        if(expireInfoDTO != null){
            expireInfo = expireInfoDTO.transferToExpireInfo();
        }
        return expireInfo;
    }

    public boolean updateExpireInfo(ExpireInfoDTO expireInfoDTO) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            boolean isExpireInfoUpdated = expireInfoDAO.updateExpireInfo(expireInfoDTO, conn);
            if(!isExpireInfoUpdated){
                throw new SQLException("Expire Information Update fails.");
            }
        }catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }

        System.out.println("Food expire Info added");
        return true;
    }

    public ArrayList<ExpireInfoDTO> queryExpireInfoClosedToExpire() throws SQLException, ClassNotFoundException{
        ArrayList<ExpireInfoDTO> expireInfoDTOs = new ArrayList<>();

        // 获取当前日期
        Date currentDate = new Date();

        // 使用 Calendar 类来增加过期天数
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, OtherConstants.DAYS_TO_SURPLUS);

        // 获取过期日期
        Date expireDate = calendar.getTime();

        Connection conn = null;
        try{
            conn = DataSource.getInstance().getConnection();
            expireInfoDTOs = expireInfoDAO.getExpireInfoByExpireDate(expireDate, conn);
        } catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }

        return expireInfoDTOs;
    }

    public ArrayList<ExpireInfoDTO> queryAllExpireInfo() throws SQLException, ClassNotFoundException{
        ArrayList<ExpireInfoDTO> expireInfoDTOs = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DataSource.getInstance().getConnection();
            expireInfoDTOs = expireInfoDAO.getAllExpireInfo(conn);
        } catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }

        return expireInfoDTOs;
    }

    public List<Inventory> getDonationInventories() throws SQLException,ClassNotFoundException{
        List<Inventory> donationInventories= null;
        Connection conn = null;
        try{
            conn = DataSource.getInstance().getConnection();
            donationInventories = inventoryDAO.getDonationInventories(conn);
        } catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }

        return donationInventories;
    }

    public HashMap<Food, Integer[]> getFoodSurplusSummary() throws SQLException, ClassNotFoundException{
        HashMap<Food, Integer[]> foodMap = new HashMap<>();
        HashMap<Integer, Integer[]> surplusMap = new HashMap<>();
        Connection conn = null;
        try{
            conn = DataSource.getInstance().getConnection();
            surplusMap = expireInfoDAO.getFoodSurplusSummary(conn);

            if(!surplusMap.isEmpty()){
                for (Map.Entry<Integer, Integer[]> entry : surplusMap.entrySet()) {
                    int foodId = entry.getKey();
                    Integer[] qty = entry.getValue();

                    Food food = foodDAO.getFoodById(foodId, conn);
                    foodMap.put(food, qty);
                }
            }
        } catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }

        return foodMap;
    }

    public HashMap<Food,Integer[]> getAllInventoryData() throws SQLException, ClassNotFoundException{
        HashMap<Food,Integer[]> inventoryMap = new HashMap<Food,Integer[]>();
        HashMap<Integer, Integer[]> dataFromDAOMap = new HashMap<>();
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            dataFromDAOMap = inventoryDAO.getAllInventoryData(conn);

            if(!dataFromDAOMap.isEmpty()){
                for (Map.Entry<Integer, Integer[]> entry : dataFromDAOMap.entrySet()) {
                    int foodId = entry.getKey();
                    Integer[] qty = entry.getValue();

                    Food food = foodDAO.getFoodById(foodId, conn);
                    inventoryMap.put(food, qty);
                }
            }

        } catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }
        
        return inventoryMap;  
    }

    /*
    public ArrayList<Transaction> getTransactions() throws SQLException{
        ArrayList<Transaction> transactions = null;

        Connection conn = null;

        conn = DataSource.getInstance().getConnection();
        ArrayList<TransactionDTO> dtos = transactionDAO.getAllTransactions(conn);
        if(!dtos.isEmpty()){
            for(TransactionDTO dto: dtos){
                Food food = foodDAO.getFoodById(dto.getFoodId(), conn);
                UserDTO userDTO = userDAO.getUserById(0, conn);
                User user = UserFactory.createUser(userDTO.getType());
                user = userDTO.transferToUser(user);
                Order order = null;
                if(dto.getFoodId() != 0 && dto.getFoodId() != null){
                    order = OrderDAO.
                }


            }
        }
    }
    */

}
