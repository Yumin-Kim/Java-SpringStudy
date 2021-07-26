package jpacore.jpashop.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteItem {
    private String name;
    private Long itemId;

    public static FavoriteItem createFavoriteItem(Long getId, String getName) {
        return new FavoriteItem(getName, getId);
    }
}
