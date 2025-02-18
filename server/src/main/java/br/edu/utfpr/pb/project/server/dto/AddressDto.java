package br.edu.utfpr.pb.project.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@JsonIgnoreProperties("users")
public class AddressDto {

    private Long id;

    private String cep;
    private String street;
    private String number;
    private String complement;
    private String district; //bairro
    private String city;
    private String state;
    @JsonIgnore
    private UserDto user;
}
