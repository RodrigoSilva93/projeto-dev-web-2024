package br.edu.utfpr.pb.project.server.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private int id;

    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Date birthDate;

    @NotNull
    private String gender;

    @NotNull
    private String cpf;

    @NotNull
    private String phone;
}
