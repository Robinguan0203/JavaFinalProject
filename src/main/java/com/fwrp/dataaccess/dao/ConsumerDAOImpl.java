/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.UserDTO;
import com.fwrp.models.Consumer;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author YAOZHOU XIE
 */
public class ConsumerDAOImpl implements ConsumerDAO {

    @Override
    public boolean addConsumer(UserDTO userDTO, Connection conn) throws SQLException {
        boolean isSuccess = false;

        try (PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO users (firstname, lastname, email, phone, password, type, organization) VALUES(?, ?, ?, ?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, userDTO.getFirstName());
            pstmt.setString(2, userDTO.getLastName());
            pstmt.setString(3, userDTO.getEmail());
            pstmt.setString(4, userDTO.getPhone());
            pstmt.setString(5, userDTO.getPassword());
            pstmt.setInt(6, userDTO.getType());
            pstmt.setString(7, userDTO.getOrganization());

            if (pstmt.executeUpdate() == 1) {
                isSuccess = true;
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userDTO.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }

        return isSuccess;
    }

    @Override
    public UserDTO getConsumerById(int id, Connection conn) throws SQLException {
        UserDTO userDTO = null;

        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userDTO = new UserDTO(
                            rs.getInt("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("password"),
                            rs.getInt("type"),
                            rs.getString("organization")
                    );
                }
            }
        }

        return userDTO;
    }

    @Override
    public ArrayList<UserDTO> getAllConsumers(Connection conn) throws SQLException {
        ArrayList<UserDTO> consumers = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE type = ?")) {
            pstmt.setInt(1, 1); // Assuming type 1 is for consumers

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    UserDTO userDTO = new UserDTO(
                            rs.getInt("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("password"),
                            rs.getInt("type"),
                            rs.getString("organization")
                    );
                    consumers.add(userDTO);
                }
            }
        }

        return consumers;
    }

    @Override
    public boolean updateConsumer(UserDTO userDTO, Connection conn) throws SQLException {
        boolean isSuccess = false;

        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE users SET firstname = ?, lastname = ?, email = ?, phone = ?, password = ?, type = ?, organization = ? WHERE id = ?")) {

            pstmt.setString(1, userDTO.getFirstName());
            pstmt.setString(2, userDTO.getLastName());
            pstmt.setString(3, userDTO.getEmail());
            pstmt.setString(4, userDTO.getPhone());
            pstmt.setString(5, userDTO.getPassword());
            pstmt.setInt(6, userDTO.getType());
            pstmt.setString(7, userDTO.getOrganization());
            pstmt.setInt(8, userDTO.getId());

            if (pstmt.executeUpdate() == 1) {
                isSuccess = true;
            }
        }

        return isSuccess;
    }

    @Override
    public boolean removeConsumer(int id, Connection conn) throws SQLException {
        boolean isSuccess = false;

        try (PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM users WHERE id = ?")) {

            pstmt.setInt(1, id);

            if (pstmt.executeUpdate() == 1) {
                isSuccess = true;
            }
        }

        return isSuccess;
    }
}
