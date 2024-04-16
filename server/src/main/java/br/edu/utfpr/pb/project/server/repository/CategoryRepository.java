package br.edu.utfpr.pb.project.server.repository;

import br.edu.utfpr.pb.project.server.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
