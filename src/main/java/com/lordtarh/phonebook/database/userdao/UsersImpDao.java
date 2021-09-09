package com.lordtarh.phonebook.database.userdao;

import com.lordtarh.phonebook.database.dbConnections;
import com.lordtarh.phonebook.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsersImpDao implements UsersDao<Users> {
    Connection connection = dbConnections.getConnection();
    private static Logger LOG = LoggerFactory.getLogger(UsersImpDao.class);


    public void create(Users users) {
        String query ="insert into users (name,family,phone) values (?,?,?)";
        PreparedStatement statement = null;
        int status = 0;
        try {
             statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,users.getName());
            statement.setString(2,users.getFamily());
            statement.setString(3,users.getPhone());
            status = statement.executeUpdate();
            LOG.info("{} {} Create !",users.getName(),users.getFamily());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (status==0){
            try {
                throw new SQLException("Creating user failed, no rows affected.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                users.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void update(Users users) {
        String query="update users set name=? , family =? , phone =? where id=?";
        PreparedStatement statement =null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,users.getName());
            statement.setString(2,users.getFamily());
            statement.setString(3,users.getPhone());
            statement.setInt(4,users.getId());
            statement.executeUpdate();
            LOG.info("{} {} Updated ...",users.getName(),users.getFamily());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void  delete(int id) {
        PreparedStatement statement =null;
        String query="delete from users where id=?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Users> search(String name) {
        List<Users> list = new ArrayList<>();
        PreparedStatement statement = null;
        String query="select * from users where name LIKE ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,"%"+name+"%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(Users.builder().id(resultSet.getInt("id")).name(resultSet.getString("name"))
                        .family(resultSet.getString("family")).phone(resultSet.getString("phone")).build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }
}
