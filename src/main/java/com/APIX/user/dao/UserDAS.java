package com.APIX.user.dao;
import com.APIX.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository("UserDAS")
public class UserDAS implements UserDAO{
    private static List<User> db = new ArrayList<>();
    long id=0;
    @Override
    public boolean addUser(User user) {

        for (User oldUser:db) {
            if (Objects.equals(oldUser.getUsername(), user.getUsername())) {
                return false;
            }
        }
        id++;
        user.setId(id);
        db.add(user);
        return true;
    }

    @Override
    public List<User> getUsers() {
        return db;
    }

    @Override
    public boolean removeUserById(long id) {
        User user=findUserById(id);
        if(user!=null){
            db.remove(user);
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(long id) {
        return findUserById(id);

    }

    @Override
    public User updateUser(long id , User updatedUser) {
        for (User usr:db
             ) {
            if(Objects.equals(usr.getUsername(), updatedUser.getUsername())){
                return null;
            }

        }
        User user=findUserById(id);
        user.setBalance(updatedUser.getBalance());
        user.setEmail(updatedUser.getEmail());
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        return user;
    }
    private User findUserById(long id) {
        for (User user : db) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
