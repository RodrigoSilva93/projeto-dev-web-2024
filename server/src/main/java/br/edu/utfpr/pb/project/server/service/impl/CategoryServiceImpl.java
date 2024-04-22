package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.model.Category;
import br.edu.utfpr.pb.project.server.repository.CategoryRepository;
import br.edu.utfpr.pb.project.server.service.ICategoryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends CrudServiceImpl<Category, Long> implements ICategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }
}
