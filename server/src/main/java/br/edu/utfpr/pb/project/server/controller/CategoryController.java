package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.CategoryDto;
import br.edu.utfpr.pb.project.server.model.Category;
import br.edu.utfpr.pb.project.server.service.ICategoryService;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categories")
public class CategoryController extends CrudController<Category, CategoryDto, Long>{

    private final ICategoryService service;
    private final ModelMapper modelMapper;

    public CategoryController(ICategoryService service, ModelMapper modelMapper) {
        super(Category.class, CategoryDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<Category, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}
