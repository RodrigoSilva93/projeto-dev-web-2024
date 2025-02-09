package br.edu.utfpr.pb.project.server.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShoppingCartProductDto {
    private Long productId;
    private Integer quantity;
    private BigDecimal finalPrice;
}
