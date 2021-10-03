package com.dbals0.dockerspringdeme.repository;

import com.dbals0.dockerspringdeme.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
