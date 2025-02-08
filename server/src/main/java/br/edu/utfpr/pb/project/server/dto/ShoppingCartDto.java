package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.enums.PaymentStatus;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class ShoppingCartDto {
    private int id;

    private Date dateTime;
    private UserDto user;
    private PaymentStatus payment;
    private Double totalPurchase;
    private List<ShoppingCartProductDto> products;
}
