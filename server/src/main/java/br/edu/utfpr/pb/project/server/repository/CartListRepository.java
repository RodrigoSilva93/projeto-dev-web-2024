package br.edu.utfpr.pb.project.server.repository;

import br.edu.utfpr.pb.project.server.model.CartList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartListRepository extends JpaRepository<CartList, Long> {
}
