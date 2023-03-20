package com.ngoctin.intuition.IntuitionStore.endpoints.order;

import com.ngoctin.intuition.IntuitionStore.entities.order.Order;
import com.ngoctin.intuition.IntuitionStore.entities.order.OrderDetail;
import com.ngoctin.intuition.IntuitionStore.entities.promotion.Promotion;
import com.ngoctin.intuition.IntuitionStore.models.order.OrderDetailResponse;
import com.ngoctin.intuition.IntuitionStore.models.order.OrderHistoryResponse;
import com.ngoctin.intuition.IntuitionStore.services.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/order")
@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderResource {

    private final OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody Order order){
        if(orderService.createOrder(order)){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(false);
    }

    @PostMapping("/createOrderDetails")
    public ResponseEntity<?> createOrderDetail(@RequestBody List<OrderDetail> orderDetailList){
        if(orderService.createOrderDetails(orderDetailList)){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(false);
    }

    @GetMapping("/getOrderHistoryByUserID/{userID}")
    public ResponseEntity<?> getOrderHistoryByUserID(@PathVariable("userID") int userID){
        List<Order> responses =
                orderService.getOrderHistoryListByUserID(userID);
       log.warn("Controller : " + responses);
        if(responses != null){
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Not found !");
    }

    @GetMapping("/getOrderDetailsByOrderID/{orderID}")
    public ResponseEntity<?> getOrderHistoryByUserID(@PathVariable("orderID") Long orderID){
        List<OrderDetailResponse> responses =
                orderService.getOrderDetailsByOrderID(orderID);
        if(responses != null){
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Not found !");
    }



}
