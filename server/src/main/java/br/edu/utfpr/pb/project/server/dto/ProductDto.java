package br.edu.utfpr.pb.project.server.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private int id;

    private String name;
    private String description;
    private BigDecimal price;
    private Double discount;
    private String image;
    private CategoryDto category;
}
