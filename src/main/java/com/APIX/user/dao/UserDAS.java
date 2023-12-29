package com.APIX.user.dao;
import com.APIX.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository("UserDAS")
public class UserDAS implements UserDAO{
    private static List<User> db = new ArrayList<>();
    static {
        initDummyData();
    }

    private static void initDummyData() {
        User user1 = new User(1L, "john", "john@gmail.com", "123456789", 1000, "helwan","01111111111");
        User user2 = new User(2L,"abdo", "abdo@gmail.com", "123456789", 2000, "maadi","01111111111");
        User user3 = new User(3L, "Hossam", "hossamaf15@gmail.com", "123456789", 3000, "maadi","01111111111");
        User user4 = new User(4L, "Hatem", "hatem101@yahoo.com", "123456789", 4000, "Bolak dakrour","01111111111");

        db.add(user1);
        db.add(user2);
        db.add(user3);
        db.add(user4);
    }
    @Override
    public boolean addUser(User user) {

        for (User oldUser:db) {
            if (Objects.equals(oldUser.getUsername(), user.getUsername())) {
                return false;
            }
        }
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
