/**
 * @author Darshan soni
 * @version 1.0
 */
package com.yash.onlineHomeDecor.util;

import com.yash.onlineHomeDecor.domain.Order;
import com.yash.onlineHomeDecor.exception.OrderException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/onlinehomedecor";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}




 class OrderUtil extends DBUtil {
    public static Order parseOrderFromRequest(HttpServletRequest request) throws OrderException {
        try {
            // Parse request parameters and create Order object
            Order order = new Order();
            // Set order properties
            return order;
        } catch (Exception e) {
            throw new OrderException("Error parsing order data: " + e.getMessage());
        }
    }

    public static String convertOrderToJson(Order order) throws OrderException {
        try {
            // Convert order object to JSON string
            String jsonString = "";
            return jsonString;
        } catch (Exception e) {
            throw new OrderException("Error converting order to JSON: " + e.getMessage());
        }
    }
}