package com.lordtarh.phonebook.database.userdao;

import com.lordtarh.phonebook.model.Users;

import java.util.List;

public class UsersImpDao implements UsersDao<Users> {

    public int create(Users users) {
        return 0;
    }

    public Users update(Users users) {
        return null;
    }

    public boolean delete(int id) {
        return false;
    }

    public List<Users> search(String name) {
        return null;
    }
}
