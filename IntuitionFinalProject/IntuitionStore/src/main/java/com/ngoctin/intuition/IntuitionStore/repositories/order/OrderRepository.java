package com.ngoctin.intuition.IntuitionStore.repositories.order;

import com.ngoctin.intuition.IntuitionStore.entities.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long>,MyOrderRepository {
}
