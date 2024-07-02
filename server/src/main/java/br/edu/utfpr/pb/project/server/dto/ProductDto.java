package br.edu.utfpr.pb.project.server.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private int id;

    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    private String description;

    @NotNull
    private BigDecimal price;
    private Double discount;
    private String image;
    private Integer quantity;

    private CategoryDto category;
}
