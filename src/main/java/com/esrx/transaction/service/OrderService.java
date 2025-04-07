package com.esrx.transaction.service;

import com.esrx.transaction.dao.OrderRepo;
import com.esrx.transaction.entity.Inventory;
import com.esrx.transaction.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    AuditService auditService;
    @Transactional(propagation = Propagation.REQUIRED)
    public Order saveOrder(Order order){
        System.out.println("Order transaction "+ TransactionSynchronizationManager.getCurrentTransactionName());
        System.out.println("Order transaction Active/NotActive "+ TransactionSynchronizationManager.isActualTransactionActive());
        Inventory product=inventoryService.findProductById(order.getProductId());
        validateProductAvailability(order, product);
        product.setQuantity(product.getQuantity()-order.getQuantity());
        inventoryService.updateProduct(product);
        order.setPurchaseDate(LocalDateTime.now());
        order.setAmount(product.getPrice()*order.getQuantity());
        order.setPrice(product.getPrice());
        Order placeOrder= orderRepo.save(order);
        auditService.getAuditDetails(placeOrder,"Order places successfully");
        return placeOrder;
    }

    private void validateProductAvailability(Order order, Inventory product) {
        if(order.getQuantity()> product.getQuantity()){
            auditService.getAuditDetails(order,"Order cant be places due to some error");
            throw new IllegalArgumentException("Quantity is less in inventory");
        }
    }


    public List<Order> saveAllOrders(List<Order> orders){
        for(Order order:orders){
            Inventory product=inventoryService.findProductById(order.getProductId());
            order.setPurchaseDate(LocalDateTime.now());
            order.setAmount(product.getPrice()*order.getQuantity());
            product.setQuantity(product.getQuantity()-order.getQuantity());
            inventoryService.updateProduct(product);
        }
        return orderRepo.saveAll(orders);
    }
    public Order findByOrderId(int id){
        return orderRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid OrderID"));
    }
    public Order updateOrder(Order order){
        Order updatedOrder=findByOrderId(order.getId());
        Inventory product=inventoryService.findProductById(order.getProductId());
        updatedOrder.setPurchaseDate(LocalDateTime.now());
        updatedOrder.setAmount(product.getPrice()*order.getQuantity());
        product.setQuantity(product.getQuantity()-updatedOrder.getQuantity());
        inventoryService.updateProduct(product);
        orderRepo.save(updatedOrder);
        return updatedOrder;
    }
    public String cancelOrder(Order order){
        Inventory product=inventoryService.findProductById(order.getProductId());
        product.setQuantity(product.getQuantity()+order.getQuantity());
        inventoryService.updateProduct(product);
        orderRepo.delete(order);
        return "Order cancelled successfully";
    }
}
