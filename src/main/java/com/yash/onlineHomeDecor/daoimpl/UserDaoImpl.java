package com.yash.onlineHomeDecor.daoimpl;

import com.yash.onlineHomeDecor.dao.UserDao;
import com.yash.onlineHomeDecor.domain.User;
import com.yash.onlineHomeDecor.exception.UserNotFoundException;
import com.yash.onlineHomeDecor.util.DBUtil;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;


public class UserDaoImpl implements UserDao {

    private static final String INSERT_USER_SQL = "INSERT INTO users (name, email, password, address, date_of_birth, role) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String UPDATE_USER_SQL = "UPDATE users SET name = ?, email = ?, password = ?, address = ?, date_of_birth = ?, role = ? WHERE id = ?";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?";


    @Override
    public void addUser(User user) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword()); // Consider hashing
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setDate(5, new java.sql.Date(user.getDateOfBirth().getTime()));
            preparedStatement.setString(6, user.getRole());
            preparedStatement.executeUpdate();

            // Retrieve generated ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        User user = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = extractUserFromResultSet(rs);
            } else {
                throw new UserNotFoundException("User not found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        User user = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = extractUserFromResultSet(rs);
            } else {
                throw new UserNotFoundException("User not found with email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = extractUserFromResultSet(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateUser(User user) throws UserNotFoundException {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword()); // Consider hashing
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setDate(5, new java.sql.Date(user.getDateOfBirth().getTime()));
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setInt(7, user.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new UserNotFoundException("User not found with ID: " + user.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteUser(int id) throws UserNotFoundException {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new UserNotFoundException("User not found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password")); // Handle securely
        user.setAddress(rs.getString("address"));
        user.setDateOfBirth(rs.getDate("date_of_birth"));
        user.setRole(rs.getString("role"));
        return user;
    }
}
