package br.edu.utfpr.pb.project.server.model;

import br.edu.utfpr.pb.project.server.enums.CategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "tb_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryType type;
}
