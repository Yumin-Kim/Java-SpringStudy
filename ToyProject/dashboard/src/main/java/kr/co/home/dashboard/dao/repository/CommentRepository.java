package kr.co.home.dashboard.dao.repository;

import kr.co.home.dashboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
