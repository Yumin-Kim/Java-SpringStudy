package springcore.fastcamp.fastcamp.domin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springcore.fastcamp.fastcamp.dto.ProductRequestDto;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
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

    public Product(ProductRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myprice = 0;
    }
}
