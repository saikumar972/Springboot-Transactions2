package com.esrx.transaction.service;

import com.esrx.transaction.entity.AuditOrder;
import com.esrx.transaction.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class NotificationService {
    @Transactional(propagation = Propagation.NEVER)
    public void sendNotification(){
        System.out.println("sending notification to customer");
    }
}
