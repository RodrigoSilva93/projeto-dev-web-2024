package br.edu.utfpr.pb.project.server.service;

import br.edu.utfpr.pb.project.server.model.Category;
import br.edu.utfpr.pb.project.server.model.Product;

import java.util.List;

public interface IProductService extends ICrudService <Product, Long> {
    List<Product> findByCategory(Category category);
    Category findCategoryById(Long id);

}
