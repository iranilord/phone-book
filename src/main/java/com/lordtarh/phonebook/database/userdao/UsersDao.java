package com.lordtarh.phonebook.database.userdao;


import java.util.List;

public interface UsersDao <T> {
    public int create(T t);
    public T update(T t);
    public boolean delete(int id);
    public List<T> search(String name);
}
