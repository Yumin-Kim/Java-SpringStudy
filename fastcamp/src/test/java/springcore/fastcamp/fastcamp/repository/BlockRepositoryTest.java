package springcore.fastcamp.fastcamp.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import springcore.fastcamp.fastcamp.domin.Block;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BlockRepositoryTest {

    @Autowired
    private BlockRepository blockRepository;

    @Test
    @DisplayName("Block 엔티티 Test")
    @Transactional
    @Rollback(value = false)
    public void crudTest() throws Exception{
        //given
//        Block block = new Block("name1");
//        //when
//        block.setName("martin");
//        block.setReason("안녕하세요");
//        block.setStartDate(LocalDate.now());
//        block.setEndDate(LocalDate.now());
//        blockRepository.save(block);
//        List<Block> all = blockRepository.findAll();
//        //then
//
//        assertThat(all.size()).isEqualTo(1);
    }

}