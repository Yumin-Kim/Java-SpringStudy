package springcore.fastcamp.fastcamp.repository;

import org.hibernate.validator.internal.util.privilegedactions.LoadClass;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import springcore.fastcamp.fastcamp.domin.*;
import springcore.fastcamp.fastcamp.dto.UserInfoDto;

import javax.persistence.EntityManager;
import java.awt.*;
import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
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

    @Test
    @DisplayName("User 엔티티에서의 월,일별로 회원 찾기")
    @Transactional
    @Rollback(value = false)
    void findMemberBirth() throws Exception{
        //given
        makeUser();
        //when
        List<User> byBirthMouthOfBirth = userRepository.findByBirthMouthOfBirth(10);
        List<User> byBirthDayOfBirth = userRepository.findByBirthDayOfBirth(3);
        List<User> byMouthOfBirth = userRepository.findByMouthOfBirth(10);
        //then
        Assertions.assertNotNull(byBirthMouthOfBirth);
        Assertions.assertNotNull(byBirthDayOfBirth);
        Assertions.assertNotNull(byMouthOfBirth);
        assertThat(byBirthMouthOfBirth.size()).isEqualTo(4);
        assertThat(byMouthOfBirth.size()).isEqualTo(4);
    }

    private void makeUser() {
        User createUser1 = User.builder()
                .name("name1")
                .cityCode("cityCode")
                .birth(new Birth(LocalDate.of(2020,10,10)))
                .build();
        User createUser4 = User.builder()
                .name("name4")
                .cityCode("cityCode")
                .birth(new Birth(LocalDate.of(1991, 10, 31)))
                .build();
        User createUser5 = User.builder()
                .name("name5")
                .cityCode("cityCode")
                .birth(new Birth(LocalDate.of(1991, 10, 31)))
                .build();
        User createUser6 = User.builder()
                .name("name6")
                .cityCode("cityCode")
                .birth(new Birth(1993, 10, 6))
                .build();


        userRepository.save(createUser1);
        userRepository.save(createUser5);
        userRepository.save(createUser6);
        userRepository.save(createUser4);
    }

    @Test
    @DisplayName("orphanRemoval = true")
    @Transactional
    @Rollback(value = false)
    void orphanRemovalTest() throws Exception{
        //given
        Address address = new Address("city_Code", "city_city");
        House createHouse = House.builder()
                .houseSize(1000)
                .name("아파트1")
                .address(address)
                .build();
        User createUser = User.builder()
                .name("name123434")
                .cityCode("cityCode")
                .house(createHouse)
                .build();
        Job development = Job.builder()
                .address(address)
                .name("development")
                .price(3000)
                .user(createUser)
                .build();
        jobRepository.save(development);
        //when
        development.setUser(null);
        createUser.setHouse(null);
        jobRepository.save(development);
        userRepository.save(createUser);
        //then
        System.out.println("development = " + development.getName());
        userRepository.findAll().forEach(data-> System.out.println("data.getName() " +data.getName()));
    }

    @Test
    @DisplayName("일대일 관계 CRUD")
    @Transactional
//    @Rollback(value = false)
    void OneToOneCRUD() throws Exception{
        //given
        Address address = new Address("city_Code", "city_city");
        House createHouse = House.builder()
                .houseSize(1000)
                .name("아파트1")
                .address(address)
                .build();
        User createUser = User.builder()
                .name("name12")
                .cityCode("cityCode")
                .house(createHouse)
                .build();
        Job development = Job.builder()
                .address(address)
                .name("development")
                .price(3000)
                .user(createUser)
                .build();
        jobRepository.save(development);
        User name12 = userRepository.findByName("name12");
        User name11 = userRepository.findByName("name12");
        System.out.println("name12 = " + name12.getName()+name12);
        System.out.println("createUser.getName() = " + name11.getName()+name11);
        Set<User> userHashMap = new HashSet<>();
        userHashMap.add(name12);
        userHashMap.add(createUser);
        HashMap<Integer, Integer> numberHash = new HashMap<>();
        numberHash.put(1, 1);
        numberHash.put(2, 1);
        
        int size = userHashMap.size();
        System.out.println("size = " + size);
        System.out.println("numberHash.size() = " + numberHash.size());
    }

    @Transactional
    @Rollback(value = false)
    @DisplayName("Input Data")
    @Disabled
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
        List<Job> all = jobRepository.findAll();
        User findUser = userRepository.findByName("hello01");
        Optional<Job> getJobInfo = jobRepository.findById(1L);
        //then
        if(all.size()  > 0){
            String userName = all.get(0).getUser().getName();
            getJobInfo.ifPresent(job->{
                assertThat(job.getUser().getName()).isEqualTo("hello01");
            });
            assertThat(findUser.getName()).isEqualTo(userName);
        }
        getJobInfo.orElseGet(() -> {
            System.out.println("실패");
            jobRepository.findJobToUserInfo(2L).ifPresent(findToUser -> {
                System.out.println(findToUser.getUser().getName());
                assertThat(findToUser.getUser().getName()).isEqualTo("hello");
            });
            return null;
        });
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("사용자 삭제(deleted 멤버 요소를 기반으로 삭제 후 삭제된 정보 확인 쿼리 생성")
    @Transactional
    @Rollback(value = false)
    void deleteUserTest() throws Exception{
        //given
        makeUser();
        User name1 = userRepository.findByName("name1");
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setDeleted(true);
        //when
        name1.updateUserEntity(userInfoDto);
        userRepository.save(name1);
        //then
        assertThat(name1.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("isDeleted false 확인")
    @Transactional
    @Rollback(value = false)
    void isDeletedFindAll_Test() throws Exception{
        //given
        List<User> allUses = userRepository.findAll();
        //when
        System.out.println("allUses.size() = " + allUses.size());
        //then
    }

    @Test
    @DisplayName("update")
    @Transactional
    @Rollback(value = false)
    void modifyUserInfo() throws Exception{
        //given
        User name1 = userRepository.findByName("name1");

        //when

        //then
    }

}
