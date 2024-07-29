/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.UserDTO;
import com.fwrp.models.Consumer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Not use, change to UserDAO
 * @author YAOZHOU XIE
 */
public interface ConsumerDAO {
    boolean addConsumer(UserDTO userDTO, Connection conn) throws SQLException;
    UserDTO getConsumerById(int id, Connection conn) throws SQLException;
    ArrayList<UserDTO> getAllConsumers(Connection conn) throws SQLException;
    boolean updateConsumer(UserDTO userDTO, Connection conn) throws SQLException;
    boolean removeConsumer(int id, Connection conn) throws SQLException;
}
