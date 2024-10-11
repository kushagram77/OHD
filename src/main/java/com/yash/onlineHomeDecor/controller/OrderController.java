package com.yash.onlineHomeDecor.controller;

import com.yash.onlineHomeDecor.exception.OrderException;
import com.yash.onlineHomeDecor.service.OrderService;
import com.yash.onlineHomeDecor.serviceimpl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/*")
public class OrderController extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init() {
        orderService = new OrderServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            switch (pathInfo) {
                case "/create":
                    createOrder(request, response);
                    break;
                case "/update":
                    updateOrder(request, response);
                    break;
                case "/delete":
                    deleteOrder(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (OrderException e) {
            handleError(response, e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            switch (pathInfo) {
                case "/view":
                    viewOrder(request, response);
                    break;
                case "/user-orders":
                    getUserOrders(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (OrderException e) {
            handleError(response, e);
        }
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, OrderException {
        // Implementation
    }

    // Other controller methods...
}