package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.model.Product;
import br.edu.utfpr.pb.project.server.model.ShoppingCart;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShoppingCartProductDto {
    private Long id;
    private ShoppingCart shoppingCart;
    private Product product;
    private Integer quantity;
    private BigDecimal finalPrice;
}
