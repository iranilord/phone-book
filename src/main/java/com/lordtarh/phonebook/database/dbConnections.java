package com.lordtarh.phonebook.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class dbConnections {
    private static Connection connection;
    private static dbConnections dbConnections;
    private String url,username,password;
    private dbConnections(){
        readConfig();
        System.out.println(url+username+password);
        getConnectiondb();

    }


    private Connection getConnectiondb(){
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    private void readConfig(){
        Properties properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("app.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        url=properties.getProperty("url");
        password = properties.getProperty("password");
        username = properties.getProperty("username");
    }



    public static Connection getConnection(){
        if (dbConnections == null)
            dbConnections = new dbConnections();
        return connection;
    }


}
