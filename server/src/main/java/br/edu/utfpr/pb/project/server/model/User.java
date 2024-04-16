package br.edu.utfpr.pb.project.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull
    @Size(min = 6)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "{br.edu.utfpr.pb.project.server.user.password.Pattern}")
    private String password;

    @NotNull
    private Date birthDate;

    @NotNull
    private String gender;

    @NotNull
    @Size(max = 14, message = "Cpf must be 14 characters")
    private String cpf;

    @NotNull
    private String phone;

    @NotNull
    private String cep;
    private String country;
    private String state;
    private String city;
    private String district;
    private String street;
    private Integer number;
    private String reference;

}
