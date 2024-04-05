package com.juliomesquita.jpaspecification.infra.persistense.specification;

import com.juliomesquita.jpaspecification.infra.data.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<Product> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);

    }

    public static Specification<Product> hasPriceGreterThan(BigDecimal price){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("price"), price);
    }
}
