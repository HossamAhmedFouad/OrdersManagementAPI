package com.APIX.user.service;

import com.APIX.CustomRepository;
import com.APIX.product.model.Product;
import com.APIX.product.service.ProductService;
import com.APIX.user.dao.UserDAO;
import com.APIX.user.dao.UserDAS;
import com.APIX.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class UserService {

    static UserDAO userDAO;
    @Autowired
    public UserService( UserDAO userDAO) {
        UserService.userDAO = userDAO;
    }
    public static boolean addUser(User user) {
        return userDAO.addUser(user);
    }


    public static List<User> getUsers() {
        return userDAO.getUsers();
    }


    public static boolean removeUserById(long id) {
        return userDAO.removeUserById(id);
    }

    public static User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    public static User updateUser(long id , User user) {
        return userDAO.updateUser(id,user);

    }
}
