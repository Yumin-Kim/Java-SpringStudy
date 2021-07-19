package springcore.fastcamp.fastcamp.domin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import springcore.fastcamp.fastcamp.dto.ProductRequestDto;

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
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myprice = 0;
        this.id = userId;
    }
}
