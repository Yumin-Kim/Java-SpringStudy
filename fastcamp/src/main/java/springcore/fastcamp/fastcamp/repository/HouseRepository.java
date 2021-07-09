package springcore.fastcamp.fastcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springcore.fastcamp.fastcamp.domin.House;

public interface HouseRepository extends JpaRepository<House,Long> {
}
