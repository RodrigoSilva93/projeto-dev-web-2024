package br.edu.utfpr.pb.project.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cep;

    @NotNull
    private String street;

    @NotNull
    private String number;

    private String complement;

    @NotNull
    private String district; //bairro

    @NotNull
    private String city;

    @NotNull
    private String state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
