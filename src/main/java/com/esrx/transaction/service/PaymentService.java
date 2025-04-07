package com.esrx.transaction.service;

import com.esrx.transaction.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
@Service
public class PaymentService {
    @Transactional(propagation = Propagation.MANDATORY)
    public void validatePayment(Order placeOrder) {
        System.out.println("mandatory transaction "+ TransactionSynchronizationManager.getCurrentTransactionName());
        System.out.println("mandatory transaction Active/NotActive "+ TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("validating payment");
    }
}
