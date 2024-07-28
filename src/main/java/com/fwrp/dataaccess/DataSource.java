

package com.fwrp.dataaccess;

import com.fwrp.constants.FileLocationConstant;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class manages the database connection with Singleton.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class DataSource {

    private static final String DB_URL;
    private static final String DB_USERNAME;
    private static final String DB_PASSWORD;

    static {
        Properties properties = new Properties();
        try {
            properties.load(DataSource.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        DB_URL = properties.getProperty("db.url");
        DB_USERNAME = properties.getProperty("db.username");
        DB_PASSWORD = properties.getProperty("db.password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * The singleton instance of the DBConnection class.
     */
    private static DataSource instance;
    
    /**
     * The active database connection.
     */
    private static Connection connection = null;
    
    /**
     * Private constructor for the DataSource class.
     * Initializes the database connection.
     */
    private DataSource() throws ClassNotFoundException{
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database Connection Creation Failed : " + e.getMessage());
        }
    }
    
    /**
     * Returns the database connection.
     * @return the database connection
     */
    public Connection getConnection() {
        return connection;
    }
    
    /**
     * Returns the instance of the DataSource class.
     * If the instance is null or the connection is closed, a new instance is created.
     * @return the instance of the DataSource class
     * @throws java.lang.ClassNotFoundException
     */
    public static DataSource getInstance() throws ClassNotFoundException{
        if (instance == null) {
            instance = new DataSource();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new DataSource();
                }
            } catch (SQLException | NullPointerException e) {
                System.out.println("Database Connection Check Failed : " + e.getMessage());
            }
        }
        return instance;
    }    
    
    /**
     * Closes the database connection.
     */
    public static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to Close Database Connection : " + e.getMessage());
            }
        }
    }

    /**
     * Opens the properties,
     * reads the database connection details, and returns them as an array of {@code String}.
     * 
     * @return an array of {@code String} containing the database URL, username, and password
     */
    public static String[] openPropsFile() {
        // added use of Properties and try-with-resources - kriger
        Properties props = new Properties();

        try (InputStream in = Files.newInputStream(Paths.get(FileLocationConstant.DATABASE_PROPERTIES));) {
            props.load(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// catch()

        String db = props.getProperty("db");
        String name = props.getProperty("name");
        String host = props.getProperty("host");
        String password = props.getProperty("pass");
        String port = props.getProperty("port");
        String username = props.getProperty("user");
        
        String[] info = new String[3];
        info[0] = "jdbc:mysql://" + host + ":" + port + "/" + name;
        info[1] = username;
        info[2] = password;

        return info;
     
    }
}
