package br.edu.utfpr.pb.project.server.service;

import br.edu.utfpr.pb.project.server.model.CartList;
import br.edu.utfpr.pb.project.server.model.CartListId;
import br.edu.utfpr.pb.project.server.model.Product;
import br.edu.utfpr.pb.project.server.model.ShoppingCart;
import br.edu.utfpr.pb.project.server.repository.ShoppingCartRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService implements ICrudService <ShoppingCart, Long> {

    private final ShoppingCartRepository shoppingCartRepository;
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void addProductToCart(ShoppingCart shoppingCart, Product product, Integer quantity) {
        CartListId cartListId = new CartListId(product.getId(), shoppingCart.getId());
        CartList cartListItem = new CartList(cartListId, product.getPrice(), quantity);

        shoppingCart.getCartLists().add(cartListItem);

        shoppingCartRepository.save(shoppingCart);
    }

    public void removeProductFromCart(ShoppingCart shoppingCart, Long productId) {
        List<CartList> cartLists = shoppingCart.getCartLists();
        cartLists.removeIf(cartList -> cartList.getId().getIdProduct().equals(productId));
        shoppingCartRepository.save(shoppingCart);
    }

    public void closeShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setStatus(false);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public List<ShoppingCart> findAll() {
        return List.of();
    }

    @Override
    public List<ShoppingCart> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<ShoppingCart> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ShoppingCart save(ShoppingCart entity) {
        return null;
    }

    @Override
    public ShoppingCart saveAndFlush(ShoppingCart entity) {
        return null;
    }

    @Override
    public Iterable<ShoppingCart> save(Iterable<ShoppingCart> iterable) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public ShoppingCart findOne(Long aLong) {
        return null;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void delete(Iterable<? extends ShoppingCart> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
