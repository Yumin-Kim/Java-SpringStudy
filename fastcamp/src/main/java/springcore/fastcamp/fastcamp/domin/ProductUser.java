package springcore.fastcamp.fastcamp.domin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "product_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductUser extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_user_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = true)
    private Long kakaoId;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productUser")
    private List<Product> products = new ArrayList<>();

    public ProductUser(String name, String password, String email, UserRole role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.kakaoId = null;
    }

    public ProductUser(String name, String password, String email, UserRole role, Long kakaoId) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.kakaoId = kakaoId;
    }
}


