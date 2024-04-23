package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.model.CartList;
import br.edu.utfpr.pb.project.server.model.Product;
import br.edu.utfpr.pb.project.server.repository.CartListRepository;
import br.edu.utfpr.pb.project.server.repository.ProductRepository;
import br.edu.utfpr.pb.project.server.service.ICartListService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

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

        Optional<Product> product = productRepository.findById(savedCartList.getId().getIdProduct());
        product.ifPresent(value -> savedCartList.setPrice(
                updatePriceByQuantity(value.getPrice(), savedCartList.getQuantity(), value.getDiscount())
        ));

        return cartListRepository.save(savedCartList);
    }

    @Override
    protected JpaRepository<CartList, Long> getRepository() {
        return cartListRepository;
    }
}
