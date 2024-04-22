package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.ProductDto;
import br.edu.utfpr.pb.project.server.model.Product;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import br.edu.utfpr.pb.project.server.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController extends CrudController<Product, ProductDto, Long> {

    private final IProductService service;
    private final ModelMapper modelMapper;

    public ProductController(IProductService service, ModelMapper modelMapper) {
        super(Product.class, ProductDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<Product, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}
