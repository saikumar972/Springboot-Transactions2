package com.esrx.transaction.service;

import com.esrx.transaction.dao.AuditRepo;
import com.esrx.transaction.entity.AuditOrder;
import com.esrx.transaction.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuditService {
    @Autowired
    AuditRepo auditRepo;
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void getAuditDetails(Order order, String action){
        AuditOrder auditOrder=new AuditOrder();
        auditOrder.setAction(action+" "+order.toString());
        auditOrder.setTimeStamp(LocalDateTime.now());
        auditRepo.save(auditOrder);
    }
}
