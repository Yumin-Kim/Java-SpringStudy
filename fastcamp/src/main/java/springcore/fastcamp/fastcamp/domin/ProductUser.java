package springcore.fastcamp.fastcamp.domin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "product_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductUser extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public ProductUser(String name, String password, String email, UserRole role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}

