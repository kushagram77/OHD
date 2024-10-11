package com.yash.onlineHomeDecor.service;


import com.yash.onlineHomeDecor.domain.Order;
import com.yash.onlineHomeDecor.domain.OrderItem;
import com.yash.onlineHomeDecor.exception.OrderException;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order) throws OrderException, Exception;
    Order getOrderById(Long orderId) throws OrderException;
    List<Order> getOrdersByUserId(Long userId) throws OrderException;
    void updateOrder(Order order) throws OrderException;
    void deleteOrder(Long orderId) throws OrderException;
    Double calculateTotalAmount(List<OrderItem> items);
}
