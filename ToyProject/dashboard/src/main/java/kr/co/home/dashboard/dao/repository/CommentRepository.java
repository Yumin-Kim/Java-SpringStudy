package kr.co.home.dashboard.dao.repository;

import kr.co.home.dashboard.domain.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("select c from Comment c" +
            " join fetch c.dashboard d" +
            " join fetch c.member m" +
            " where d.id = :dashboardId")
    List<Comment> findByDashBoardId(@Param("dashboardId") Long dashboardId);

    @EntityGraph(attributePaths = {"member"})
    Optional<Comment> findMemberById(Long commentId);
}
