package jpacore.jpashop.repository.dto;

import jpacore.jpashop.domain.Coupon;
import jpacore.jpashop.domain.item.Album;
import jpacore.jpashop.domain.item.Book;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.domain.item.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@ToString
public class CouponDto {

    private String name;
    private int salePercent;
    private LocalDateTime eventStartPoint;
    private LocalDateTime eventEndPoint;

    @NotEmpty(message = "아이템 정보가 null값일 수 없습ㄴ디ㅏ.")
    private ItemDto itemDto;

    public CouponDto(Coupon coupon) {
        name = coupon.getName();
        salePercent = coupon.getSalePercent();
        eventStartPoint = coupon.getEventStartPoint();
        eventEndPoint = coupon.getEventEndPoint();
    }

    public CouponDto(Coupon saveCoupon, Book book) {
        name = saveCoupon.getName();
        salePercent = saveCoupon.getSalePercent();
        eventStartPoint = saveCoupon.getEventStartPoint();
        eventEndPoint = saveCoupon.getEventEndPoint();
        this.itemDto = new ItemDto((Book) saveCoupon.getItem(), book.getArtist(), book.getEtc());
    }

    public CouponDto(Coupon saveCoupon, Album album) {
        name = saveCoupon.getName();
        salePercent = saveCoupon.getSalePercent();
        eventStartPoint = saveCoupon.getEventStartPoint();
        eventEndPoint = saveCoupon.getEventEndPoint();
        this.itemDto = new ItemDto((Album) saveCoupon.getItem(), album.getAuthor(), album.getIsbn());
    }

    public CouponDto(Coupon saveCoupon, Movie movie) {
        name = saveCoupon.getName();
        salePercent = saveCoupon.getSalePercent();
        eventStartPoint = saveCoupon.getEventStartPoint();
        eventEndPoint = saveCoupon.getEventEndPoint();
        this.itemDto = new ItemDto((Movie) saveCoupon.getItem(), movie.getActor(), movie.getDirector());
    }
}
