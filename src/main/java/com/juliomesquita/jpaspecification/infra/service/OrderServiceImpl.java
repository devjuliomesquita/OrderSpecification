package com.juliomesquita.jpaspecification.infra.service;

import com.juliomesquita.jpaspecification.infra.data.entities.Order;
import com.juliomesquita.jpaspecification.infra.data.enums.Status;
import com.juliomesquita.jpaspecification.infra.persistense.repositories.OrderRepository;
import com.juliomesquita.jpaspecification.infra.persistense.specification.OrderSpecification;
import com.juliomesquita.jpaspecification.infra.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public Page<Order> searchOrders(
            String customerName,
            Status status,
            String productCategory,
            BigDecimal productPrice,
            Pageable pageable
    ) {
        Specification<Order> spec = Specification.where(null);
        if (customerName != null && !customerName.isEmpty()) {
            spec = spec.and(OrderSpecification.hasCosumerName(customerName));
        }

        if (productCategory != null && !productCategory.isEmpty()) {
            spec = spec.and(OrderSpecification.hasProductInCategory(productCategory));
        }

        if (status != null) {
            spec = spec.and(OrderSpecification.hasStatus(status));
        }

        if (productPrice.compareTo(BigDecimal.ZERO) < 0) {
            spec = spec.and(OrderSpecification.hasProductPriceGreaterThan(productPrice));
        }

        return this.orderRepository.findAll(spec, pageable);
    }

}
