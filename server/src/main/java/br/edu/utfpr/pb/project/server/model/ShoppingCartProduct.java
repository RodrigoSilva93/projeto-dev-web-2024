package br.edu.utfpr.pb.project.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "shopping_cart_products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShoppingCartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal finalPrice;

    public void calculateFinalPrice() {
        this.finalPrice = this.product.getPrice()
                .subtract(this.product.getPrice().multiply(BigDecimal.valueOf(this.product.getDiscount())))
                .multiply(BigDecimal.valueOf(this.quantity));
    }
}
