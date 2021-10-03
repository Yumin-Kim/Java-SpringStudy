package com.dbals0.dockerspringdeme.repository;

import com.dbals0.dockerspringdeme.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository  extends JpaRepository<Member,Long> {
}
