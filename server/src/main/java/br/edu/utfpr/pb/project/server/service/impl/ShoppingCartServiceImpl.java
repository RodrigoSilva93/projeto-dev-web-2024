package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.enums.PaymentStatus;
import br.edu.utfpr.pb.project.server.model.Product;
import br.edu.utfpr.pb.project.server.model.ShoppingCart;
import br.edu.utfpr.pb.project.server.model.ShoppingCartProduct;
import br.edu.utfpr.pb.project.server.model.User;
import br.edu.utfpr.pb.project.server.repository.ProductRepository;
import br.edu.utfpr.pb.project.server.repository.ShoppingCartRepository;
import br.edu.utfpr.pb.project.server.service.AuthService;
import br.edu.utfpr.pb.project.server.service.IShoppingCartService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl extends CrudServiceImpl<ShoppingCart, Long> implements IShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final AuthService authService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, AuthService authService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.authService = authService;
    }

    @Override
    protected JpaRepository<ShoppingCart, Long> getRepository() {
        return shoppingCartRepository;
    }

    @Override
    public ShoppingCart save(ShoppingCart entity) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        User user = (User) authService.loadUserByUsername(email);

        entity.setUser(user);

        // Verificamos os carrinhos existentes. A primeira regra é enviar a compra como "PENDING" no banco, antes
        // de finalizar a compra. Quando o usuário confirmar a compra o status muda de "PENDING" para "APPROVED".
        // Não é possível ter duas compras com status "PENDING" ao mesmo tempo.
        List<ShoppingCart> openCarts = shoppingCartRepository.findByUserAndPayment(user, PaymentStatus.PENDING);
        ShoppingCart latestPendingCart = openCarts.isEmpty() ? null : openCarts.getFirst();

        if (latestPendingCart != null) {
            if (entity.getPayment().equals(PaymentStatus.PENDING))
                updateShoppingCartProducts(latestPendingCart, entity.getShoppingCartProducts());
            else latestPendingCart.setPayment(PaymentStatus.APPROVED);
            return shoppingCartRepository.save(latestPendingCart);
        }

        ShoppingCart savedCart = shoppingCartRepository.save(entity);

        List<ShoppingCartProduct> newShoppingCartProducts = new ArrayList<>();
        for (ShoppingCartProduct shoppingCartProduct : entity.getShoppingCartProducts()) {
            Product product = productRepository.findById(shoppingCartProduct.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

            shoppingCartProduct.setShoppingCart(savedCart);
            shoppingCartProduct.setProduct(product);
            shoppingCartProduct.calculateFinalPrice();

            newShoppingCartProducts.add(shoppingCartProduct);
        }

        savedCart.getShoppingCartProducts().removeIf(existingProduct ->
                newShoppingCartProducts.stream().noneMatch(newProduct ->
                        newProduct.getProduct().getId().equals(existingProduct.getProduct().getId()))
        );

        savedCart.getShoppingCartProducts().addAll(newShoppingCartProducts);
        savedCart.updateTotalPurchase();

        return shoppingCartRepository.save(savedCart);
    }

    private void updateShoppingCartProducts(ShoppingCart existingCart, List<ShoppingCartProduct> newShoppingCartProducts) {
        existingCart.getShoppingCartProducts().clear();
        newShoppingCartProducts.forEach(shoppingCartProduct -> {
            shoppingCartProduct.setShoppingCart(existingCart);
            existingCart.getShoppingCartProducts().add(shoppingCartProduct);
        });
    }
}
