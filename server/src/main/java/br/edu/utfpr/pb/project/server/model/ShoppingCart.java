package br.edu.utfpr.pb.project.server.model;

import br.edu.utfpr.pb.project.server.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_shopping_cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shopping_cart")
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dateTime;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PaymentStatus payment;

    @NotNull
    private Double totalPurchase;

    @ManyToMany
    @JoinTable(name = "shopping_cart_products", joinColumns = @JoinColumn(name = "shopping_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    public void addProducts(List<Product> productsToAdd) {
        products.addAll(productsToAdd);
    }
}
