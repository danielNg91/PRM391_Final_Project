package com.ngoctin.intuition.IntuitionStore.services.order;

import com.ngoctin.intuition.IntuitionStore.entities.order.Order;
import com.ngoctin.intuition.IntuitionStore.entities.order.OrderDetail;
import com.ngoctin.intuition.IntuitionStore.models.order.OrderDetailResponse;
import com.ngoctin.intuition.IntuitionStore.models.order.OrderHistoryResponse;
import com.ngoctin.intuition.IntuitionStore.repositories.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public boolean createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    @Override
    public boolean createOrderDetails(List<OrderDetail> orderDetailList) {
        return orderRepository.createOrderDetail(orderDetailList);
    }

    @Override
    public List<Order> getOrderHistoryListByUserID(int userID) {
        log.warn("Controller : " + orderRepository.getOrderHistoryListByUserID(userID));
        return orderRepository.getOrderHistoryListByUserID(userID);
    }

    @Override
    public List<OrderDetailResponse> getOrderDetailsByOrderID(Long orderID) {
        return orderRepository.getOrderDetailsByOrderID(orderID);
    }
}
