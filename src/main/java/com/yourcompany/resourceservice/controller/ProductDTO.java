package com.yourcompany.resourceservice.controller;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDTO {
    private Long productId;
    private String name;
    private Double price;
}
