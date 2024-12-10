package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.model.User;
import lombok.Data;

@Data
public class AddressDto {

    private Long id;

    private String cep;
    private String street;
    private String number;
    private String complement;
    private String district; //bairro
    private String city;
    private String state;
    private User user;
}