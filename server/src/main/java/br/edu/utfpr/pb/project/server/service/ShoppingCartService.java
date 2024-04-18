package br.edu.utfpr.pb.project.server.service;

import br.edu.utfpr.pb.project.server.model.ShoppingCart;
import br.edu.utfpr.pb.project.server.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
        for (CartList cartList : shoppingCart.getCartLists()) {
            if (cartList.getId().getIdProduct().equals(productId)) {
                shoppingCart.getCartLists().remove(cartList);
                break;
            }
        }
        shoppingCartRepository.save(shoppingCart);
    }

    public void closeShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setStatus(false);
        shoppingCartRepository.save(shoppingCart);
    }
}
