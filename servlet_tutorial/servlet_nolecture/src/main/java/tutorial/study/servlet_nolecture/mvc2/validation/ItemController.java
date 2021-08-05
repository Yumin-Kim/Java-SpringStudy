package tutorial.study.servlet_nolecture.mvc2.validation;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.StringUtils.hasText;

@RestController
@Slf4j
public class ItemController {

    @PostMapping("/add")
    public String addItemV1(@Valid ItemDto itemDto) {
        Map<String, String> errors = new HashMap<>();
        log.info("itemdto={}",itemDto.toString());
        if (itemDto.getName() == null) {
            errors.put("itemName", "상품이 존재 X");
        }
        return "test";
    }

    @PostMapping("/add/v2")
    public String addItemV2(ItemDto itemDto, BindingResult bindResult) {
        if (!hasText(itemDto.getName())) {
            bindResult.addError(new FieldError("itmeDto","name","필수 입니다."));
        }
        return bindResult.toString();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    private static class ItemDto {
        @NotBlank
        private String name;
        @NotNull
        @Range(min = 20,max = 1000)
        private int price;
    }


}
