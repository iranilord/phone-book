package com.lordtarh.phonebook.database.userdao;


import java.sql.SQLException;
import java.util.List;

public interface UsersDao <T> {
    public void create(T t) ;
    public void update(T t);
    public void delete(int id);
    public List<T> search(String name);
}
