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

    List<User> findByBirthMouthOfBirth(int mouth);

    List<User> findByBirthDayOfBirth(int day);
    //함수지을때 findBy객체명,찾을 객체 멤버변수 명ㅇ

    @Query(value = "select * from users where mouth_of_birth = :mouthOfBirth",nativeQuery = true)
    List<User> findByMouthOfBirth(@Param("mouthOfBirth") int month);
}
