package com.ngoctin.intuition.IntuitionStore.repositories.order;

import com.ngoctin.intuition.IntuitionStore.ApplicationUtils;
import com.ngoctin.intuition.IntuitionStore.entities.order.Order;
import com.ngoctin.intuition.IntuitionStore.entities.order.OrderDetail;
import com.ngoctin.intuition.IntuitionStore.entities.product.Product;
import com.ngoctin.intuition.IntuitionStore.models.order.OrderDetailResponse;
import com.ngoctin.intuition.IntuitionStore.models.order.OrderHistoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MyOrderRepositoryImpl implements MyOrderRepository{

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public boolean createOrder(Order order) {
        try {
            String sql = "INSERT INTO orders " +
                    "VALUES(?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(sql,Order.class);
            query.setParameter(1,order.getId());
            query.setParameter(2,order.getUserID());
            if(order.getPromotionId() == 0){
                query.setParameter(3,null);
            }
            query.setParameter(3,order.getPromotionId());
            query.setParameter(4,order.getAddressId());
            query.setParameter(5, order.getOrderPrice());
            query.setParameter(6,ApplicationUtils.getCurrentTime());
            query.setParameter(7,order.getReceriverAddress());
            if(query.executeUpdate() > 0){
                return true;
            }
            return false;
        } catch (Exception exception) {
            log.error("Exception" + exception.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public boolean createOrderDetail(List<OrderDetail> orderDetailList) {
        try {
            String sql = "";
            int flag = 0;
            int i = 0;
            for (OrderDetail orderDetail : orderDetailList) {
                sql = "INSERT INTO order_details " +
                        "VALUES(?,?,?,?,?)";
                i++;
                Query query = entityManager.createNativeQuery(sql,OrderDetail.class);
                query.setParameter(1,System.currentTimeMillis() + i);
                query.setParameter(2,orderDetail.getProductID());
                query.setParameter(3,orderDetail.getQuantity());
                query.setParameter(4,orderDetail.getPrice());
                query.setParameter(5,orderDetail.getOrderID());
                if(query.executeUpdate() > 0){
                    flag++;
                }
            }
            if(flag == orderDetailList.size()){
                return true;
            }
            return false;
        } catch (Exception exception) {
            log.error("Exception" + exception.getMessage());
            return false;
        }
    }

    @Override
    public List<Order> getOrderHistoryListByUserID(int userID) {
        try {
            String sql = "SELECT * " +
                    "FROM orders o " +
                    "WHERE o.user_id = ?";
            Query query = entityManager.createNativeQuery(sql,Order.class);
            query.setParameter(1,userID);
            List<Order> orders = query.getResultList();
            if(orders != null){
                return orders;
            }
            return null;
        } catch (Exception exception) {
            log.error("Exception" + exception.getMessage());
            return null;
        }
    }

    @Override
    public List<OrderDetailResponse> getOrderDetailsByOrderID(Long orderID) {
        try {
            String sql ="SELECT od.id as id, p.name as product_name,od.quantity as quantity, (p.price * od.quantity ) AS price " +
                    "FROM order_details od JOIN products p ON od.product_id = p.id " +
                    "WHERE order_id = ?";
            Query query = entityManager.createNativeQuery(sql,OrderDetailResponse.class);
            query.setParameter(1,orderID);
            List<OrderDetailResponse> orderDetailList = query.getResultList();
            if(orderDetailList != null){
                return orderDetailList;
            }
            return null;
        }catch (Exception exception){
            return null;
        }
    }
}
