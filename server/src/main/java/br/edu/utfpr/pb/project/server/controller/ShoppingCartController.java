package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.ShoppingCartDto;
import br.edu.utfpr.pb.project.server.model.Product;
import br.edu.utfpr.pb.project.server.model.ShoppingCart;
import br.edu.utfpr.pb.project.server.model.ShoppingCartProduct;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import br.edu.utfpr.pb.project.server.service.IShoppingCartService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("shopping-cart")
public class ShoppingCartController extends CrudController<ShoppingCart, ShoppingCartDto, Long>{
    private final IShoppingCartService service;
    private final ModelMapper modelMapper;

    private final ICrudService<Product, Long> productRepository;
    private final ICrudService<ShoppingCart, Long> shoppingCartRepository;

    public ShoppingCartController(IShoppingCartService service, ModelMapper modelMapper,
                                  ICrudService<Product, Long> productRepository, ICrudService<ShoppingCart, Long> shoppingCartRepository) {
        super(ShoppingCart.class, ShoppingCartDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    protected ICrudService<ShoppingCart, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

    @PostMapping
    public ResponseEntity<ShoppingCartDto> create(@RequestBody @Valid ShoppingCartDto cartDto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setDateTime(cartDto.getDateTime());
        shoppingCart.setPayment(cartDto.getPayment());
        shoppingCart.setTotalPurchase(0.0);

        shoppingCart = shoppingCartRepository.save(shoppingCart);

        ShoppingCart finalShoppingCart = shoppingCart;
        List<ShoppingCartProduct> shoppingCartProducts = Optional.ofNullable(cartDto.getShoppingCartProducts())
                .orElse(Collections.emptyList()).stream().map(dto -> {
            Product product = productRepository.findOne(dto.getProductId());

            ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct();
            shoppingCartProduct.setShoppingCart(finalShoppingCart);
            shoppingCartProduct.setProduct(product);
            shoppingCartProduct.setQuantity(dto.getQuantity());
            shoppingCartProduct.calculateFinalPrice();

            return shoppingCartProduct;
        }).toList();

        finalShoppingCart.getShoppingCartProducts().clear();
        finalShoppingCart.getShoppingCartProducts().addAll(shoppingCartProducts);
        shoppingCart.updateTotalPurchase();

        shoppingCart = shoppingCartRepository.save(shoppingCart);

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(shoppingCart, ShoppingCartDto.class));
    }
}


