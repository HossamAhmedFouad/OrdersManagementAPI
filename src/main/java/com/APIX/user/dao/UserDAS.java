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
        for (User usr:db) {
            if(usr.getId()==id){
                db.remove(usr);
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserById(long id) {
        for (User oldUser:db) {
            if (oldUser.getId()==id) {
                return oldUser;
            }
        }
        return null;
    }

    @Override
    public User updateUser(long id , User user) {
        for (User usr:db
             ) {
            if(usr.getId()==id){
                usr.setBalance(user.getBalance());
                usr.setEmail(user.getEmail());
                usr.setUsername(user.getUsername());
                usr.setPassword(user.getPassword());
                return usr;
            }

        }
        return null;
    }
}
