package br.edu.utfpr.pb.project.server.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ShoppingCartDto {
    private int id;

    private Boolean status;
    private Date dateTime;
    private UserDto user;
}
