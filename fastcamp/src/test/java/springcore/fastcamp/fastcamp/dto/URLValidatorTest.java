package springcore.fastcamp.fastcamp.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class URLValidatorTest {

    @Test
    @DisplayName("URL 형태 : 정상")
    void urlValidation() throws Exception{
        //given
        String url = "https://shopping-phinf.pstatic.net/main_8232398/82323985017.4.jpg";
        //when
        boolean isValid = URLValidator.urlValidator(url);
        //then
        assertTrue(isValid);
    }

    @Test
    @DisplayName("URL 형태: 비정상 (`://` 빠짐)")
    void urlValidator5() {
        // given
        String url = "httpfacebook.com";
        // when
        boolean isValid = URLValidator.urlValidator(url);
        // then
        assertFalse(isValid);
    }

}