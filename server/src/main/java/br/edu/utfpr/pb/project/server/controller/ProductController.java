package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.ProductDto;
import br.edu.utfpr.pb.project.server.model.Category;
import br.edu.utfpr.pb.project.server.model.Product;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import br.edu.utfpr.pb.project.server.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
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

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Long categoryId) {
        Category category = service.findCategoryById(categoryId);
        if (category != null) {
            List<Product> products = service.findByCategory(category);
            List<ProductDto> productDtos = products.stream()
                    .map(product -> modelMapper.map(product, ProductDto.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(productDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
