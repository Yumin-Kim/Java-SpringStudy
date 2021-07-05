package springcore.fastcamp.fastcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springcore.fastcamp.fastcamp.domin.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.name =:name")
    User findByName(@Param("name") String name);

    @Query("select u from User u join Job j on j.id=u.id ")
    List<User> findUserInfo();
}
