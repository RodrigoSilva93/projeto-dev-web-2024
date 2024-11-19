package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDto {

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
