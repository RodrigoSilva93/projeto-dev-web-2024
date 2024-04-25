package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.model.ShoppingCart;
import br.edu.utfpr.pb.project.server.repository.ShoppingCartRepository;
import br.edu.utfpr.pb.project.server.service.IShoppingCartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl extends CrudServiceImpl<ShoppingCart, Long> implements IShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void closeShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setStatus(true);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    protected JpaRepository<ShoppingCart, Long> getRepository() {
        return shoppingCartRepository;
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

        List<ShoppingCart> openCart = shoppingCartRepository.findByUserAndStatus(entity.getUser(), false);
        if (!openCart.isEmpty() && (!entity.getStatus())) {
            return openCart.getFirst();

        } else if (!openCart.isEmpty() )  {
            openCart.getFirst().setStatus(true);
            return shoppingCartRepository.save(openCart.getFirst());
        } else {
            return shoppingCartRepository.save(entity);
        }
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
        shoppingCartRepository.flush();
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
