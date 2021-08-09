package jpacore.jpashop;


import jpacore.jpashop.domain.*;
import jpacore.jpashop.domain.enumtype.DeliveryStatus;
import jpacore.jpashop.domain.item.Album;
import jpacore.jpashop.domain.item.Book;
import jpacore.jpashop.domain.item.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class InitDataMethod {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("데이터 Init")
    void create_1() throws Exception {
        //given
        Address address = Address.createAddress("city", "Street", "cityCode");
        Job name1 = Job.createJob("name1");
        Job name2 = Job.createJob("name2");
        Member member = Member.createMember("username1", "nickname1", "password", "email1.com", 10, address, List.of(name1, name2));
        Member member1 = Member.createMember("username2", "nickname2", "password", "email2.com", 11, address, List.of(name1, name2));
        Member member2 = Member.createMember("username3", "nickname3", "password", "email3.com", 12, address, List.of(name1, name2));

        Member member3 = Member.createMember("username1", "nickname1", "password", "email1.com", 10, address, List.of(name1, name2));
        Member member4 = Member.createMember("username2", "nickname2", "password", "email2.com", 11, address, List.of(name1, name2));
        Member member5 = Member.createMember("username3", "nickname3", "password", "email3.com", 12, address, List.of(name1, name2));

        em.persist(member3);
        em.persist(member4);
        em.persist(member5);

        Album album = Album.createAlbum("Album", 1000, "company", "author", "isbn");
        Book book = Book.createBook("Book", 1000, "company", "artist", "etc");
        Movie movie = Movie.createMovie("Movie", 1000, "company", "director", "actor");
        Album album1 = Album.createAlbum("Album1", 1000, "company1", "author1", "isbn1");
        Book book1 = Book.createBook("Book1", 1000, "company1", "artist1", "etc1");
        Movie movie1 = Movie.createMovie("Movie1", 1000, "company1", "director1", "actor1");

        em.persist(album);
        em.persist(book);
        em.persist(movie);

        em.persist(album1);
        em.persist(book1);
        em.persist(movie1);

        FavoriteItem favoriteItem = FavoriteItem.createFavoriteItem(album.getId(), album.getName());
        FavoriteItem favoriteItem1 = FavoriteItem.createFavoriteItem(book.getId(), book.getName());
        FavoriteItem favoriteItem2 = FavoriteItem.createFavoriteItem(movie.getId(), movie.getName());

        member.updateFavoriteItemList(List.of(favoriteItem1, favoriteItem2, favoriteItem));
        member1.updateFavoriteItemList(List.of(favoriteItem1, favoriteItem2, favoriteItem));
        member2.updateFavoriteItemList(List.of(favoriteItem1, favoriteItem2, favoriteItem));

        Coupon coupon1 = Coupon.createCoupone("coupon1", 12, LocalDateTime.now(), LocalDateTime.now(), album);
        Coupon coupon2 = Coupon.createCoupone("coupon2", 12, LocalDateTime.now(), LocalDateTime.now(), book);
        Coupon coupon3 = Coupon.createCoupone("coupon3", 12, LocalDateTime.now(), LocalDateTime.now(), movie);

        Coupon coupon4 = Coupon.createCoupone("coupon1", 12, LocalDateTime.now(), LocalDateTime.now(), album);
        Coupon coupon5 = Coupon.createCoupone("coupon2", 12, LocalDateTime.now(), LocalDateTime.now(), book);
        Coupon coupon6 = Coupon.createCoupone("coupon3", 12, LocalDateTime.now(), LocalDateTime.now(), movie);

        em.persist(coupon1);
        em.persist(coupon2);
        em.persist(coupon3);
        em.persist(coupon4);
        em.persist(coupon5);
        em.persist(coupon6);

        CouponMember couponeMember = CouponMember.createCouponeMember(1, 10, member, coupon1);
        CouponMember couponeMember1 = CouponMember.createCouponeMember(1, 10, member1, coupon2);
        CouponMember couponeMember2 = CouponMember.createCouponeMember(1, 10, member2, coupon3);

        em.persist(couponeMember1);
        em.persist(couponeMember2);
        em.persist(couponeMember);

        Delivery build = Delivery.builder()
                .status(DeliveryStatus.START)
                .address(address)
                .build();

        OrderItem orderItem = OrderItem.createOrderItem(1, 10, book);
        OrderItem orderItem1 = OrderItem.createOrderItem(1, 10, movie);
        OrderItem orderItem2 = OrderItem.createOrderItem(1, 10, album);

        Order order_1 = Order.createOrder(build, member1, orderItem);
        Order order_2 = Order.createOrder(build, member1, orderItem1);
        Order order_3 = Order.createOrder(build, member1, orderItem2);
        Order order1_1 = Order.createOrder(build, member2, orderItem);
        Order order1_2 = Order.createOrder(build, member2, orderItem1);
        Order order1_3 = Order.createOrder(build, member2, orderItem2);
        Order order2_1 = Order.createOrder(build, member, orderItem2);
        Order order2_2 = Order.createOrder(build, member, orderItem1);
        Order order2_3 = Order.createOrder(build, member, orderItem);
        em.persist(order_1);
        em.persist(order_2);
        em.persist(order_3);
        em.persist(order1_1);
        em.persist(order1_2);
        em.persist(order1_3);
        em.persist(order2_1);
        em.persist(order2_2);
        em.persist(order2_3);

        //when

        //then
    }

}
