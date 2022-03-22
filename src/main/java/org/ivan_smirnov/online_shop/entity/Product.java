package org.ivan_smirnov.online_shop.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private int id;
    private String name;
    private double price;
}
