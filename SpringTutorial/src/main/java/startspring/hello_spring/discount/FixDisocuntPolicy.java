package startspring.hello_spring.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import startspring.hello_spring.member.Grade;
import startspring.hello_spring.member.Member;
@Component
@Qualifier("SubDiscountPolicy")
public class FixDisocuntPolicy implements DiscountPolicy {

    private int discountFixedAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixedAmount;
        }else {
            return 0;
        }
    }
}
