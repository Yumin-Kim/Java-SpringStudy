package springcore.fastcamp.fastcamp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import springcore.fastcamp.fastcamp.domin.User;
import springcore.fastcamp.fastcamp.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class DBCrudTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    DefaultListableBeanFactory df;

    @BeforeEach
    @Transactional
    @Rollback(value = false)
    void dummyUser(){
        User buildUser1 = User.builder()
                .name("hello1")
                .cityCode("city1")
                .build();
        buildUser1.setName("asd").setCityCode("test");
        User buildUser2 = User.builder()
                .name("hello12")
                .cityCode("city12")
                .build();

        User saveUser = userRepository.save(buildUser1);
        System.out.println("saveUser = " + saveUser);
        System.out.println("buildUser1 = " + buildUser1);
        System.out.println("buildUser2 = " + buildUser2);
        System.out.println("saveUser.equals(buildUser1) = " + saveUser.equals(buildUser1));
        System.out.println("saveUser.equals(buildUser1) = " + saveUser.equals(buildUser2));
        System.out.println("buildUser1.equals(buildUser2) = " + buildUser1.equals(buildUser2));
        System.out.println("(buildUser1 == buildUser2) = " + (buildUser1 == buildUser2));
        System.out.println("(buildUser1 == saveUser) = " + (buildUser1 == saveUser));
        System.out.println("saveUser.hashCode() = " + saveUser.hashCode());
        System.out.println("buildUser1.hashCode() = " + buildUser1.hashCode());
        System.out.println("buildUser2.hashCode() = " + buildUser2.hashCode());
        userRepository.save(buildUser2);
    }

    @Test
    @DisplayName("실제 데이터 베이스 연동 확인")
    @Transactional
    @Rollback(value = false)
    void testConnection() throws Exception{
        //given
        User user = User.builder()
                .cityCode("cityCode")
                .name("name")
                .build();
        //when
        Arrays.stream(df.getBeanDefinitionNames())
        .forEach(System.out::println);
        User saveUser = userRepository.save(user);
        //then
        assertThat(saveUser.getCityCode()).isEqualTo(user.getCityCode());
    }

    @Test
    @DisplayName("update 문장입니다")
    @Transactional
    @Rollback(value = false)
    public void update() throws Exception{
        //given
        User buildUser1 = User.builder()
                .name("hello")
                .cityCode("city")
                .build();

        User buildUser2 = User.builder()
                .name("hello1")
                .cityCode("city1")
                .build();

        User saveUser1 = userRepository.save(buildUser1);
        User saveUser2 = userRepository.save(buildUser2);

        //when
        userRepository.findById(saveUser1.getId())
                .ifPresent((findUser)->{
                    findUser.setName("updateUser");
                    findUser.setCityCode("Data");
                });
        saveUser2.setName("updateUser Save");
        //then
        assertThat(saveUser1.getName()).isEqualTo("updateUser");
        assertThat(saveUser2.getName()).isEqualTo("updateUser Save");
    }

    @Test
    @DisplayName("Delete 문장 입니다")
    public void delete() throws Exception{
        //given
        Optional<User> findUser = userRepository.findById(2L);
        //when
        findUser.ifPresent(find->{
            //then
            System.out.println(find.getCityCode());
            userRepository.delete(find);
        });
    }

    @Test
    @DisplayName("통합 테스트로 진행하세요 Read 문장 입니다")
    public void read() throws Exception{
        //given
        //when
        Optional<User> findUser = userRepository.findById(2L);
        List<User> all = userRepository.findAll();
        //then
        findUser.ifPresent((user)->{
            assertThat(user.getName()).isEqualTo("updateUser");
        });
        assertThat(all.size()).isEqualTo(8);
    }

}
