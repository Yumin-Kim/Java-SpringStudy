package springcore.fastcamp.fastcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springcore.fastcamp.fastcamp.domin.Person;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    //by- where조건
    List<Person> findByName(String name);

    List<Person> findByBlockIsNull();

    List<Person> findByBloodType(String bloodType);

    @Query(value = "select * from  person where person.deleted = true",nativeQuery = true)
    List<Person> findPeopleDelete();
}
