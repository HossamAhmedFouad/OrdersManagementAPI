package com.APIX.user.dao;
import com.APIX.CustomRepository;
import com.APIX.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository("UserDAO")
public class UserDAO implements CustomRepository<User, Long> {
    private static List<User> db = new ArrayList<>();
    static {
        initDummyData();
    }

    private static void initDummyData() {
        User user1 = new User("john", "john@gmail.com", "123456789", 1000, "helwan","01111111111");
        User user2 = new User("abdo", "abdo@gmail.com", "123456789", 2000, "maadi","01111111111");
        User user3 = new User("Hossam","hossamaf15@gmail.com","123456789", 3000, "maadi","01111111111");
        User user4 = new User("Hatem", "hatem101@yahoo.com", "123456789", 4000, "Bolak dakrour","01111111111");

        db.add(user1);
        db.add(user2);
        db.add(user3);
        db.add(user4);
    }
    @Override
    public User getById(Long id) {
        for (User user : db) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean save(User user) {
        for (User oldUser:db) {
            if (Objects.equals(oldUser.getUsername(), user.getUsername())) {
                return false;
            }
        }
        db.add(user);
        return true;
    }

    @Override
    public List<User> getAll() {
        return db;
    }

    @Override
    public boolean update(User updatedUser) {
        for(int i = 0; i < db.size(); i++){
            if(db.get(i).getId() == updatedUser.getId()){
                db.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        User user = getById(id);
        if(user != null){
            db.remove(user);
            return true;
        }
        return false;
    }
}
