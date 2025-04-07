package com.esrx.transaction.dao;

import com.esrx.transaction.entity.AuditOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepo extends JpaRepository<AuditOrder , Integer> {
}
