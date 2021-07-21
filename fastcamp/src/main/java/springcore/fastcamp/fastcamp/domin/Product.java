package springcore.fastcamp.fastcamp.domin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import springcore.fastcamp.fastcamp.dto.ProductRequestDto;
import springcore.fastcamp.fastcamp.dto.URLValidator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Accessors(chain = true)
public class Product extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_user_id")
    private ProductUser productUser;

    public Product(ProductRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myprice = 0;
    }

    public Product(ProductRequestDto requestDto, Long userId) {
        // 입력값 Validation
        if (userId == null || userId < 0) {
            throw new IllegalArgumentException("회원 Id 가 유효하지 않습니다.");
        }

        if (requestDto.getTitle() == null || requestDto.getTitle().isEmpty()) {
            throw new IllegalArgumentException("저장할 수 있는 상품명이 없습니다.");
        }

        if (!URLValidator.urlValidator(requestDto.getImage())) {
            throw new IllegalArgumentException("상품 이미지 URL 포맷이 맞지 않습니다.");
        }

        if (!URLValidator.urlValidator(requestDto.getLink())) {
            throw new IllegalArgumentException("상품 최저가 페이지 URL 포맷이 맞지 않습니다.");
        }

        if (requestDto.getLprice() <= 0) {
            throw new IllegalArgumentException("상품 최저가가 0 이하입니다.");
        }

        // 관심상품을 등록한 회원 Id 저장
        this.id = userId;
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myprice = 0;
    }

//    public Product(ProductRequestDto requestDto, Long userId) {
//        this.title = requestDto.getTitle();
//        this.image = requestDto.getImage();
//        this.link = requestDto.getLink();
//        this.lprice = requestDto.getLprice();
//        this.myprice = 0;
//        this.id = userId;
//    }

}
