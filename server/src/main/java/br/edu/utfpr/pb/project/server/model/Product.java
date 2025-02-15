package br.edu.utfpr.pb.project.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "1.0")
    private Double discount;

    @Column(name = "image_name")
    private  String imageName;

    @Column(name = "content_type")
    private  String contentType;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
