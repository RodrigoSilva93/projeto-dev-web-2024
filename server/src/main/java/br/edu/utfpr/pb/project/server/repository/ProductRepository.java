package br.edu.utfpr.pb.project.server.repository;

import br.edu.utfpr.pb.project.server.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
