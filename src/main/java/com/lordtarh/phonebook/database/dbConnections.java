package com.lordtarh.phonebook.database;

import com.lordtarh.phonebook.reflections.UsersReflections;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class dbConnections {
    private static Connection connection;
    private static dbConnections dbConnections;
    private String url, username, password;


    private dbConnections() {
        readConfig();
        getConnectiondb();
        createDb();
        createTable();

    }

    public static Connection getConnection() {

        synchronized (dbConnections.class) {
            if (dbConnections == null)
                dbConnections = new dbConnections();
        }

        return connection;
    }

    private Connection getConnectiondb() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void readConfig() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("app.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("url");
        password = properties.getProperty("password");
        username = properties.getProperty("username");
    }

    private void createTable() {
        String table = UsersReflections.getUsersCAnno();
        String id = UsersReflections.getUsersPKanno();
        List<String> fileds = UsersReflections.getUsersFAnno();
        StringBuilder query =new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        query.append(table+"(");
        if (!id.equals("")){
            query.append(id+" int(11) NOT NULL auto_increment,");
        }
        for (String s :fileds) {
            if (s.equals(id))
                continue;
            query.append(s+" varchar(100) NOT NULL ,");
        }
        query.append("PRIMARY KEY ("+id+"))");



        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    private void createDb(){
        Statement statement;
        String DB = "lordPb";
        String checkTb = "CREATE DATABASE IF NOT EXISTS " + DB;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(checkTb);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
