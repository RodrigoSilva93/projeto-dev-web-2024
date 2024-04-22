package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.model.CartList;
import br.edu.utfpr.pb.project.server.repository.CartListRepository;
import br.edu.utfpr.pb.project.server.service.ICartListService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartListServiceImpl extends CrudServiceImpl<CartList, Long> implements ICartListService {

    private final CartListRepository cartListRepository;
    public CartListServiceImpl(CartListRepository cartListRepository) {
        this.cartListRepository = cartListRepository;
    }

    public void updatePriceByQuantity(Long cartListId, BigDecimal price, Integer quantity) {

        CartList cartList = cartListRepository.findById(cartListId).orElse(null);

        BigDecimal newPrice = price.multiply(BigDecimal.valueOf(quantity));
        cartList.setPrice(newPrice);
        cartListRepository.save(cartList);
    }

    @Override
    protected JpaRepository<CartList, Long> getRepository() {
        return cartListRepository;
    }
}
