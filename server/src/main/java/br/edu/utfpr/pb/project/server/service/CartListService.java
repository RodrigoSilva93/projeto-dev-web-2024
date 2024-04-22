package br.edu.utfpr.pb.project.server.service;

import br.edu.utfpr.pb.project.server.model.CartList;
import br.edu.utfpr.pb.project.server.repository.CartListRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartListService {

    private final CartListRepository cartListRepository;
    public CartListService(CartListRepository cartListRepository) {
        this.cartListRepository = cartListRepository;
    }

    public void updatePriceByQuantity(Long cartListId, BigDecimal price, Integer quantity) {

        CartList cartList = cartListRepository.findById(cartListId).orElse(null);

        BigDecimal newPrice = price.multiply(BigDecimal.valueOf(quantity));
        cartList.setPrice(newPrice);
        cartListRepository.save(cartList);
    }
}
