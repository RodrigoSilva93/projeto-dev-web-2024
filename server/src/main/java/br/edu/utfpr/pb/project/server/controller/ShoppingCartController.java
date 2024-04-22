package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.ShoppingCartDto;
import br.edu.utfpr.pb.project.server.model.ShoppingCart;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import br.edu.utfpr.pb.project.server.service.IShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shopping-cart")
public class ShoppingCartController extends CrudController<ShoppingCart, ShoppingCartDto, Long>{
    private final IShoppingCartService service;
    private final ModelMapper modelMapper;

    public ShoppingCartController(IShoppingCartService service, ModelMapper modelMapper) {
        super(ShoppingCart.class, ShoppingCartDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<ShoppingCart, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}


