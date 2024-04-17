package br.edu.utfpr.pb.project.server.repository;

import br.edu.utfpr.pb.project.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
