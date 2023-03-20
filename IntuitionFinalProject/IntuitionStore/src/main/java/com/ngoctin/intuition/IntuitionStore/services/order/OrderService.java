package com.ngoctin.intuition.IntuitionStore.services.order;

import com.ngoctin.intuition.IntuitionStore.entities.order.Order;
import com.ngoctin.intuition.IntuitionStore.entities.order.OrderDetail;
import com.ngoctin.intuition.IntuitionStore.models.order.OrderDetailResponse;
import com.ngoctin.intuition.IntuitionStore.models.order.OrderHistoryResponse;

import java.util.List;

public interface OrderService {
    boolean createOrder(Order order);
    boolean createOrderDetails(List<OrderDetail> orderDetailList);
    List<Order> getOrderHistoryListByUserID(int userID);
    List<OrderDetailResponse> getOrderDetailsByOrderID(Long orderID);

}
