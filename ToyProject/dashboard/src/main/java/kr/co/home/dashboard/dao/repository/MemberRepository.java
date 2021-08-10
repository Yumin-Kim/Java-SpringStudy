package kr.co.home.dashboard.dao.repository;

import kr.co.home.dashboard.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
