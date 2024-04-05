package com.juliomesquita.jpaspecification.infra.service.interfaces;

import com.juliomesquita.jpaspecification.infra.data.entities.Order;
import com.juliomesquita.jpaspecification.infra.data.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface OrderService {
    Page<Order> searchOrders(
            String customerName,
            Status status,
            String productCategory,
            BigDecimal productPrice,
            Pageable pageable
    );
}
