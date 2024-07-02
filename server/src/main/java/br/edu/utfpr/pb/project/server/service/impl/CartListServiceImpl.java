package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.model.CartList;
import br.edu.utfpr.pb.project.server.repository.CartListRepository;
import br.edu.utfpr.pb.project.server.repository.ProductRepository;
import br.edu.utfpr.pb.project.server.service.ICartListService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartListServiceImpl extends CrudServiceImpl<CartList, Long> implements ICartListService {

    private final CartListRepository cartListRepository;
    private final ProductRepository productRepository;

    public CartListServiceImpl(CartListRepository cartListRepository, ProductRepository productRepository) {
        this.cartListRepository = cartListRepository;
        this.productRepository = productRepository;
    }

    public BigDecimal updatePriceByQuantity(BigDecimal price, Integer quantity, Double discount) {
        return (price.multiply(BigDecimal.valueOf(quantity))).multiply(BigDecimal.valueOf(1 - discount));
    }

    @Override
    public CartList save(CartList cartList) {

        CartList savedCartList = cartListRepository.save(cartList);

        savedCartList.getProducts().forEach(product ->
                savedCartList.setPrice(
                        updatePriceByQuantity(product.getPrice(), product.getQuantity(), product.getDiscount())
                )
        );

        return cartListRepository.save(savedCartList);
    }

    @Override
    protected JpaRepository<CartList, Long> getRepository() {
        return cartListRepository;
    }
}
