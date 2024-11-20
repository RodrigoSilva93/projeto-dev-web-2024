package br.edu.utfpr.pb.project.server.model;

import br.edu.utfpr.pb.project.server.enums.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

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
    private Boolean status;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PaymentType payment;

    //faltou recuperar o cart-list sem precisar separar as requisições
    /*exemplo
    {
        "status": false,
        "dateTime": "2024-11-20T19:00:00",
        "payment": "APPROVED",
        "cartlist": {
            ...
        }
    }

    * */
}
