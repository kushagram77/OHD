package com.yash.onlineHomeDecor.dao;


import com.yash.onlineHomeDecor.domain.Order;
import com.yash.onlineHomeDecor.exception.OrderException;

import java.util.List;

public interface OrderDao {
    Order createOrder(Order order) throws OrderException;
    Order getOrderById(Long orderId) throws OrderException;
    List<Order> getOrdersByUserId(Long userId) throws OrderException;
    void updateOrder(Order order) throws OrderException;
    void deleteOrder(Long orderId) throws OrderException;
}

