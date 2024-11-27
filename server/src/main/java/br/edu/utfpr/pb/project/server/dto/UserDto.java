package br.edu.utfpr.pb.project.server.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private int id;

    private String name;
    private String email;
    private String password;
    private Date birthDate;
    private String gender;
    private String cpf;
    private String phone;
    private List<AddressDto> addresses;
}
