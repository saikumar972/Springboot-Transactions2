package com.esrx.transaction.controller;

import com.esrx.transaction.entity.Order;
import com.esrx.transaction.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/save")
    public Order saveOrder(@RequestBody Order order){
       return orderService.saveOrder(order);
    }
    @PostMapping("/saveAll")
    public List<Order> saveAllOrders(@RequestBody List<Order> orders){
        return orderService.saveAllOrders(orders);
    }
    @GetMapping("/id/{id}")
    public Order findByOrderId(@PathVariable int id){
        return orderService.findByOrderId(id);
    }
    @PutMapping("/update")
    public Order updateOrder(@RequestBody Order order){
       return orderService.updateOrder(order);
    }
    @DeleteMapping("/delete")
    public String cancelOrder(@RequestBody Order order){
      return orderService.cancelOrder(order);
    }
}
