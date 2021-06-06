package startspring.hello_spring.order;

import startspring.hello_spring.discount.DiscountPolicy;
import startspring.hello_spring.member.Member;
import startspring.hello_spring.member.MemberRepository;
import startspring.hello_spring.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new FixDisocuntPolicy();
    // 할인정책의 로직을 수정하기 위해서 클라이언트를 수정하는 일 발생
    // SOLID 원칙에서 OCP , DIP 위반하는 행위
    //DIP로 접근해서 봤을때 인터페이스는 지켜지고 있으나 구현체는 기존에 FixedDiscountPolicy에 의존하고 있었으나 RateDiscountPolicy로 변경되는 모습을
    // 이로 인해 DIP 위반 (interface와 구현체 의존성 둘다 확인해야한다.) >> 해결하기 위해 Interface에만 의존해야한다 >> 현재 클래스에 interface로 구현된 구현체를 주입해주어야한다.
    //OCP로 접근할때 OrderServiceImpl을 변경하기 떄문에 OCP위반이다(클래스 수정!)
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(DiscountPolicy discountPolicy,MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member findMemberId = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(findMemberId, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
