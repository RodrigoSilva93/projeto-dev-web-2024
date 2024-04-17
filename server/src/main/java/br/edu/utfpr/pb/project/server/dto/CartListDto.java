package br.edu.utfpr.pb.project.server.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartListDto {
    private CartListIdDto id;

    private BigDecimal price;

    private Integer quantity;
}
