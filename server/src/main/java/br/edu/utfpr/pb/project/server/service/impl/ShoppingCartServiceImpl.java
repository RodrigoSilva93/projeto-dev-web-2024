package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.enums.PaymentStatus;
import br.edu.utfpr.pb.project.server.model.ShoppingCart;
import br.edu.utfpr.pb.project.server.model.User;
import br.edu.utfpr.pb.project.server.repository.ShoppingCartRepository;
import br.edu.utfpr.pb.project.server.service.AuthService;
import br.edu.utfpr.pb.project.server.service.IShoppingCartService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl extends CrudServiceImpl<ShoppingCart, Long> implements IShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final AuthService authService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, AuthService authService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.authService = authService;
    }

    public void closeShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setPayment(PaymentStatus.APPROVED);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    protected JpaRepository<ShoppingCart, Long> getRepository() {
        return shoppingCartRepository;
    }

//    @Override
//    public ShoppingCart save(ShoppingCart entity) {
//        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        String email = (String) authentication.getPrincipal();
//        User user = (User) authService.loadUserByUsername(email);
//
//        entity.setUser(user);
//
//        List<ShoppingCart> openCart = shoppingCartRepository.findByUserAndPayment(entity.getUser(), PaymentStatus.PENDING);
//        if (!openCart.isEmpty() && (!entity.getPayment().equals(PaymentStatus.PENDING))) {
//            return openCart.getFirst();
//
//        } else if (!openCart.isEmpty() )  {
//            openCart.getFirst().setPayment(PaymentStatus.APPROVED);
//            return shoppingCartRepository.save(openCart.getFirst());
//        } else {
//            return shoppingCartRepository.save(entity);
//        }
//    }

    @Override
    public ShoppingCart save(ShoppingCart entity) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        User user = (User) authService.loadUserByUsername(email);

        entity.setUser(user);

        // Verficamos os carrinhos existentes. A primeira regra é enviar a compra como "PENDING" no banco, antes
        // de finalizar a compra. Quando o usuário confirmar a compra o status muda de "PENDING" para "APPROVED".
        // Não é possível ter duas compras com status "PENDING" ao mesmo tempo.
        List<ShoppingCart> openCarts = shoppingCartRepository.findByUserAndPayment(user, PaymentStatus.PENDING);
        ShoppingCart latestPendingCart = openCarts.isEmpty() ? null : openCarts.getFirst();

        if (latestPendingCart != null) {
            if (entity.getPayment().equals(PaymentStatus.PENDING))
                latestPendingCart.setProducts(entity.getProducts());
            else latestPendingCart.setPayment(PaymentStatus.APPROVED);
            return shoppingCartRepository.save(latestPendingCart);
        }
        else return shoppingCartRepository.save(entity);
    }
}
