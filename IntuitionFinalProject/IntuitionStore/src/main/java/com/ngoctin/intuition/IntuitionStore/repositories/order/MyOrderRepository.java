package com.ngoctin.intuition.IntuitionStore.repositories.order;

import com.ngoctin.intuition.IntuitionStore.entities.order.Order;
import com.ngoctin.intuition.IntuitionStore.entities.order.OrderDetail;
import com.ngoctin.intuition.IntuitionStore.models.order.OrderDetailResponse;
import com.ngoctin.intuition.IntuitionStore.models.order.OrderHistoryResponse;

import java.util.List;

public interface MyOrderRepository {

    boolean createOrder(Order order);
    boolean createOrderDetail(List<OrderDetail> orderDetailList);
    List<Order> getOrderHistoryListByUserID(int userID);
    List<OrderDetailResponse> getOrderDetailsByOrderID(Long orderID);

}
