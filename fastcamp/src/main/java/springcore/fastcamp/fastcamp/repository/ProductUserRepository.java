package springcore.fastcamp.fastcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springcore.fastcamp.fastcamp.domin.ProductUser;

import java.util.Optional;

public interface ProductUserRepository extends JpaRepository<ProductUser,Long> {

    Optional<ProductUser> findByname(String username);
}
