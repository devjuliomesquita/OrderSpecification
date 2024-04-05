package com.juliomesquita.jpaspecification.infra.persistense.specification;

import com.juliomesquita.jpaspecification.infra.data.entities.Order;
import com.juliomesquita.jpaspecification.infra.data.entities.Product;
import com.juliomesquita.jpaspecification.infra.data.enums.Status;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class OrderSpecification {
    public static Specification<Order> hasCosumerName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("consumer").get("name"), name);
    }

    public static Specification<Order> hasStatus(Status status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Order> hasProductInCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            Join<Order, Product> productJoin = root.join("products", JoinType.INNER);
            return criteriaBuilder.equal(productJoin.get("category"), category);
        };
    }

    public static Specification<Order> hasProductPriceGreaterThan(BigDecimal price) {
        return (root, query, criteriaBuilder) -> {
            Join<Order, Product> productJoin = root.join("products", JoinType.INNER);
            return criteriaBuilder.greaterThan(productJoin.get("price"), price);
        };
    }
}
