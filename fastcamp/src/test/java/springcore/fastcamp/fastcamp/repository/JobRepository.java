package springcore.fastcamp.fastcamp.repository;

import com.fasterxml.jackson.databind.node.LongNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springcore.fastcamp.fastcamp.domin.Job;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("select j from Job j  join fetch j.user u  where u.id =:userId")
    Optional<Job> findJobToUserInfo(@Param("userId") Long userId);
}
