package springcore.fastcamp.fastcamp.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import springcore.fastcamp.fastcamp.domin.Address;
import springcore.fastcamp.fastcamp.domin.Job;
import springcore.fastcamp.fastcamp.domin.User;

import javax.persistence.EntityManager;
import java.awt.*;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EntityManager em;

//    @BeforeEach
    @Transactional
    @Rollback(value = false)
    void insertDummyData() {
        Assertions.assertNotNull(em);
        Address address = new Address("cityCode", "city");
        User buildUser = User.builder()
                .cityCode("city01")
                .name("hello")
                .build();

        Job development = Job.builder()
                .address(address)
                .name("development")
                .price(3000)
                .user(buildUser)
                .build();
        jobRepository.save(development);
    }

    @Test
    @DisplayName("사용자 중심 일대다 관계에서의 CRUD")
    @Transactional
    @Rollback(value = false)
    @Disabled
    public void findManyToOneRelation() throws Exception {
        //given
        //when
        List<User> userInfo = userRepository.findUserInfo();
//        User hello = userRepository.findByName("hello");
//        //then
//        assertThat(hello.getName()).isEqualTo("hello");
//        assertThat(userInfo.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("직업 중심 일대다 관계에서의 CRUD")
    @Transactional
    @Rollback(value = false)
    public void findManyToOneRelation_JobTest() throws Exception{
        //given
        //when
//        List<Job> all = jobRepository.findAll();
//        User findUser = userRepository.findByName("hello");
        Optional<Job> getJobInfo = jobRepository.findById(1L);
//        String userName = all.get(0).getUser().getName();
        //then
        getJobInfo.ifPresent(job->{
            assertThat(job.getUser().getName()).isEqualTo("hello");
        });
        getJobInfo.orElseGet(() -> {
            System.out.println("실패");
            jobRepository.findJobToUserInfo(1L).ifPresent(findUser -> {
                System.out.println(findUser.getUser().getName());
                assertThat(findUser.getUser().getName()).isEqualTo("hello");
            });
            return null;
        });
//        assertThat(all.size()).isEqualTo(1);
//        assertThat(findUser.getName()).isEqualTo(userName);
    }

}
