package tutorial.study.servlet_nolecture.mvc2.validation.blogposting;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Product {

    @Positive
    private final int productNo;

    @Size(min = 4, max = 100)
    private final String name;

    @Min(0)
    @Max(99_999_999)
    private final int price;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private final BigDecimal discount;

    @PastOrPresent
    private final LocalDateTime saleStartAt;

    @Future
    private final LocalDateTime saleEndAt;

    @Past
    private final LocalDate dateOfManufacture;
}
