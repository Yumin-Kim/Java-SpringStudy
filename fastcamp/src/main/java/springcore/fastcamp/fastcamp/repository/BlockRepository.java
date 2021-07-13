package springcore.fastcamp.fastcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springcore.fastcamp.fastcamp.domin.Block;

public interface BlockRepository extends JpaRepository<Block,Long> {
}
