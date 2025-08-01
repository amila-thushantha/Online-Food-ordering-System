package com.amila.controller;

import com.amila.Service.OrderService;
import com.amila.Service.UserService;
import com.amila.model.Order;
import com.amila.model.User;
import com.amila.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;




    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String jwt) throws Exception {


        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getRestaurantsOrder(id,order_status);
        return  new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity <Order> updateOrderStatus(
            @PathVariable Long id,
            @PathVariable String orderStatus,
            @RequestHeader("Authorization") String jwt) throws Exception {


        User user = userService.findUserByJwtToken(jwt);
        Order orders = orderService.updateOrder(id,orderStatus);
        return  new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
