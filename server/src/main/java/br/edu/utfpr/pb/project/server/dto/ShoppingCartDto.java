package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.enums.PaymentType;
import lombok.Data;

import java.sql.Date;

@Data
public class ShoppingCartDto {
    private int id;

    private Boolean status;
    private Date dateTime;
    private UserDto user;
    private PaymentType payment;
}
