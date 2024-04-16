package br.edu.utfpr.pb.project.server.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Double discount;
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
