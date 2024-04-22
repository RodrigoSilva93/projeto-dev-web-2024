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
        return shoppingCartRepository.findAll();
    }

    @Override
    public List<ShoppingCart> findAll(Sort sort) {
        return shoppingCartRepository.findAll(sort);
    }

    @Override
    public Page<ShoppingCart> findAll(Pageable pageable) {
        return shoppingCartRepository.findAll(pageable);
    }

    @Override
    public ShoppingCart save(ShoppingCart entity) {
        return shoppingCartRepository.save(entity);
    }

    @Override
    public ShoppingCart saveAndFlush(ShoppingCart entity) {
        return shoppingCartRepository.saveAndFlush(entity);
    }

    @Override
    public Iterable<ShoppingCart> save(Iterable<ShoppingCart> iterable) {
        return shoppingCartRepository.saveAll(iterable);
    }

    @Override
    public void flush() {

    }

    @Override
    public ShoppingCart findOne(Long aLong) {
        return shoppingCartRepository.findById(aLong).orElse(null);
    }

    @Override
    public boolean exists(Long aLong) {
        return shoppingCartRepository.findById(aLong).isPresent();
    }

    @Override
    public long count() {
        return shoppingCartRepository.count();
    }

    @Override
    public void delete(Long aLong) {
        shoppingCartRepository.deleteById(aLong);
    }

    @Override
    public void delete(Iterable<? extends ShoppingCart> iterable) {
        shoppingCartRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        shoppingCartRepository.deleteAll();
    }
}
