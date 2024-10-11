/**
 * @author Darshan soni
 * @version 1.0
 */
package com.yash.onlineHomeDecor.service;

import com.yash.onlineHomeDecor.domain.User;
import com.yash.onlineHomeDecor.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    void registerUser(User user) throws Exception; // Consider specific exceptions
    User loginUser(String email, String password) throws Exception;
    User getUserById(int id) throws UserNotFoundException;
    User getUserByEmail(String email) throws UserNotFoundException;
    List<User> getAllUsers();
    void updateUser(User user) throws UserNotFoundException;
    void deleteUser(int id) throws UserNotFoundException;
}

