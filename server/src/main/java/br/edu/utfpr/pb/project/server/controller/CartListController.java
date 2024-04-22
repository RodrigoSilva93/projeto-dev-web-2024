package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.CartListDto;
import br.edu.utfpr.pb.project.server.model.CartList;
import br.edu.utfpr.pb.project.server.service.ICartListService;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart-list")
public class CartListController extends CrudController<CartList, CartListDto, Long>{
    private final ICartListService service;
    private final ModelMapper modelMapper;

    public CartListController(ICartListService service, ModelMapper modelMapper) {
        super(CartList.class, CartListDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<CartList, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}
