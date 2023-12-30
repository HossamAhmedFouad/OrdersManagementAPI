package com.APIX.user.service;


import com.APIX.CustomRepository;
import com.APIX.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    static CustomRepository<User, Long> userDAO;
    @Autowired
    public UserService( CustomRepository<User, Long> userDAO) {
        UserService.userDAO = userDAO;
    }
    public static boolean addUser(User user) {
        return userDAO.save(user);
    }


    public static List<User> getUsers() {
        return userDAO.getAll();
    }


    public static boolean removeUserById(long id) {
        return userDAO.delete(id);
    }

    public static User getUserById(long id) {
        return userDAO.getById(id);
    }

    public static boolean updateUser(User user) {
        return userDAO.update(user);
    }
}
