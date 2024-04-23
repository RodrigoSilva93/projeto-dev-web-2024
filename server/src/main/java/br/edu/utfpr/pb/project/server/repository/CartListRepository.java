package br.edu.utfpr.pb.project.server.repository;

import br.edu.utfpr.pb.project.server.model.CartList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartListRepository extends JpaRepository<CartList, Long> {
}
