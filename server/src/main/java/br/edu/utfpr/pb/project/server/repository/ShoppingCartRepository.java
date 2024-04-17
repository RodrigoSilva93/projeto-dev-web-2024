package br.edu.utfpr.pb.project.server.repository;

import br.edu.utfpr.pb.project.server.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
