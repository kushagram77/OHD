/**
 * @author Darshan soni
 * @version 1.0
 */
package com.yash.onlineHomeDecor.serviceimpl;

import com.yash.onlineHomeDecor.dao.UserDao;
import com.yash.onlineHomeDecor.daoimpl.UserDaoImpl;
import com.yash.onlineHomeDecor.domain.User;
import com.yash.onlineHomeDecor.exception.UserNotFoundException;
import com.yash.onlineHomeDecor.service.UserService;
import com.yash.onlineHomeDecor.util.PasswordUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) throws Exception {
        // Check if user with the same email exists
        try {
            userDao.getUserByEmail(user.getEmail());
            throw new Exception("User with this email already exists.");
        } catch (UserNotFoundException e) {
            // Proceed to register
            // Hash the password before storing
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
            userDao.addUser(user);
        }
    }

    @Override
    public User loginUser(String email, String password) throws Exception {
        try {
            User user = userDao.getUserByEmail(email);
            if (PasswordUtil.verifyPassword(password, user.getPassword())) {
                return user;
            } else {
                throw new Exception("Invalid password.");
            }
        } catch (UserNotFoundException e) {
            throw new Exception("User not found.");
        }
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        return userDao.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void updateUser(User user) throws UserNotFoundException {
        // If password is being updated, hash it
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
        }
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int id) throws UserNotFoundException {
        userDao.deleteUser(id);
    }
}

