package com.lordtarh.phonebook;

import com.lordtarh.phonebook.database.dbConnections;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            dbConnections.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
