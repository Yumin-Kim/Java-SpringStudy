package tutorial.study.servlet_nolecture.mvc2.validation.blogposting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    @DisplayName("테스트 코드 작성")
    void start_1() throws Exception{
        //given
        Product product = new Product(0, "화장품", 13000, BigDecimal.valueOf(5.5), LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(10), LocalDate.now().minusMonths(3));
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Product>> validate = validator.validate(product);

        validate.stream()
                .forEach(System.out::println);

        //then
    }

}