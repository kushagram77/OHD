package com.yash.onlineHomeDecor.serviceimpl;

import com.yash.onlineHomeDecor.dao.OrderDao;
import com.yash.onlineHomeDecor.dao.ProductDao;
import com.yash.onlineHomeDecor.daoimpl.OrderDaoImpl;
import com.yash.onlineHomeDecor.daoimpl.ProductDaoImpl;
import com.yash.onlineHomeDecor.domain.Order;
import com.yash.onlineHomeDecor.domain.OrderItem;
import com.yash.onlineHomeDecor.domain.Product;
import com.yash.onlineHomeDecor.exception.OrderException;
import com.yash.onlineHomeDecor.exception.OrderNotFoundException;
import com.yash.onlineHomeDecor.exception.ProductNotFoundException;
import com.yash.onlineHomeDecor.service.OrderService;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public Order createOrder(Order order) throws Exception {
        try {
            // Validate order
            if (order.getUserId() <= 0) {
                throw new Exception("Invalid user ID");
            }
            if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
                throw new Exception("Order must contain at least one item");
            }

            // Calculate total amount and check inventory
            double totalAmount = 0;
            for (OrderItem item : order.getOrderItems()) {
                Product product = productDao.getProductById(item.getProductId());

                // Check inventory
                if (product.getQuantity() < item.getQuantity()) {
                    throw new Exception("Insufficient inventory for product: " + product.getName());
                }

                // Update inventory
                product.setQuantity(product.getQuantity() - item.getQuantity());
                productDao.updateProduct(product);

                // Calculate item total
                double itemTotal = product.getPrice() * item.getQuantity();
                totalAmount += itemTotal;
            }

            order.setOrderDate(new Date());
            order.setTotalAmount(totalAmount);
            order.setStatus("PENDING");

            return orderDao.createOrder(order);
        } catch (Exception e) {
            throw new Exception("Error creating order: " + e.getMessage());
        }
    }

    @Override
    public Order getOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) throws OrderException {
        return List.of();
    }

    @Override
    public void updateOrder(Order order) throws OrderException {

    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }

    @Override
    public Double calculateTotalAmount(List<OrderItem> items) {
        return 0.0;
    }

    @Override
    public Order getOrderById(int orderId) throws OrderNotFoundException {
        return orderDao.getOrderById(orderId);
    }

    @Override
    public List<Order> getOrdersByUser(int userId) {
        return orderDao.getOrdersByUser(userId);
    }

    @Override
    public void updateOrderStatus(int orderId, String status) throws OrderNotFoundException {
        Order order = orderDao.getOrderById(orderId);
        order.setStatus(status);
        orderDao.updateOrder(order);
    }

    @Override
    public void cancelOrder(int orderId) throws Exception {
        Order order = orderDao.getOrderById(orderId);

        if ("DELIVERED".equals(order.getStatus())) {
            throw new Exception("Cannot cancel a delivered order");
        }

        // Return items to inventory
        for (OrderItem item : order.getOrderItems()) {
            Product product = productDao.getProductById(item.getProductId());
            product.setQuantity(product.getQuantity() + item.getQuantity());
            productDao.updateProduct(product);
        }

        order.setStatus("CANCELLED");
        orderDao.updateOrder(order);
    }

    @Override
    public List<Order> getRecentOrders(int limit) {
        return orderDao.getRecentOrders(limit);
    }
}