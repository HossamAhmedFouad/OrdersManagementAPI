package com.APIX.user.dao;

import com.APIX.user.model.User;

import java.util.List;


public interface UserDAO {
    boolean addUser(User user);
    List<User> getUsers();
    boolean removeUserById(long id);
    User getUserById(long id);
    User updateUser(long id,User user);
}
