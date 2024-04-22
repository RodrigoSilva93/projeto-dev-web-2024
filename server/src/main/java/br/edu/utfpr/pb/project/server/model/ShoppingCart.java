package br.edu.utfpr.pb.project.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
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
    private Boolean status;

    @NotNull
    private Date dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //TODO retornar a lista de carrinhos
    public <E> List<E> getCartLists() {
        return null;
    }
}
