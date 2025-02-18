package br.edu.utfpr.pb.project.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
