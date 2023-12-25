package com.APIX.user.service;

import com.APIX.user.dao.UserDAO;
import com.APIX.user.dao.UserDAS;
import com.APIX.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }


    public List<User> getUsers() {
        return userDAO.getUsers();
    }


    public boolean removeUserById(long id) {
        return userDAO.removeUserById(id);
    }

    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    public User updateUser(long id , User user) {
        return userDAO.updateUser(id,user);

    }
}
