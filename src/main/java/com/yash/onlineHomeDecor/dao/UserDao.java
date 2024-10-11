/**
 * @author Darshan soni
 * @version 1.0
 */
package com.yash.onlineHomeDecor.dao;

import com.yash.onlineHomeDecor.domain.User;
import com.yash.onlineHomeDecor.exception.UserNotFoundException;

import java.util.List;

public interface UserDao {

    void addUser(User user);
    User getUserById(int id) throws UserNotFoundException;
    User getUserByEmail(String email) throws UserNotFoundException;
    List<User> getAllUsers();
    void updateUser(User user) throws UserNotFoundException;
    void deleteUser(int id) throws UserNotFoundException;

}
