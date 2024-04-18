import br.edu.utfpr.pb.project.server.model.ShoppingCart;
import br.edu.utfpr.pb.project.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByUser(User user);
    List<ShoppingCart> findByUserAndStatus(User user, Boolean status);
}