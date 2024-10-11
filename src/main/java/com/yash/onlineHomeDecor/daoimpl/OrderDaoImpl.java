package com.yash.onlineHomeDecor.daoimpl;


import com.yash.onlineHomeDecor.dao.OrderDao;
import com.yash.onlineHomeDecor.domain.Order;
import com.yash.onlineHomeDecor.domain.OrderItem;
import com.yash.onlineHomeDecor.domain.User;
import com.yash.onlineHomeDecor.exception.OrderException;
import com.yash.onlineHomeDecor.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    DBUtil dbUtil;
    Connection connection;


    @Override
    public Order createOrder(Order order) throws OrderException {
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO orders (user_id, order_date, total_amount) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setLong(1, order.getUserId());
            pstmt.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            pstmt.setDouble(3, order.getTotalAmount());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                order.setOrderId(rs.getLong(1));
                insertOrderItems(order);
            }

            connection.commit();
            return order;

        } catch ( SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new OrderException("Rollback failed: " + ex.getMessage());
            }
            throw new OrderException("Error creating order: " + e.getMessage());
        }
    }



    private void insertOrderItems(Order order) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity, total_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (OrderItem orderItem : order.getOrderItems()) {
                pstmt.setLong(1, orderItem.getOrderId());
                pstmt.setLong(2, orderItem.getProductId());
                pstmt.setInt(3, orderItem.getQuantity());
                pstmt.setDouble(4, orderItem.getTotalPrice());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }


    @Override
    public Order getOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) throws OrderException {
        List<Order> ls =new ArrayList<>();
        return ls;
//        return List.of([]);
    }

    @Override
    public void updateOrder(Order order) throws OrderException {

    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }
}