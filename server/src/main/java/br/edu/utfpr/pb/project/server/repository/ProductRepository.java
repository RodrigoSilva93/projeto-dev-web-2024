package br.edu.utfpr.pb.project.server.repository;

import br.edu.utfpr.pb.project.server.model.Category;
import br.edu.utfpr.pb.project.server.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByCategory(Category category);
}

