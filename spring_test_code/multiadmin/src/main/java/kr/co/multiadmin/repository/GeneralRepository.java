package kr.co.multiadmin.repository;

import kr.co.multiadmin.domain.General;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneralRepository extends JpaRepository<General,Long> {
    Optional<General> findByUsername(String username);
}
