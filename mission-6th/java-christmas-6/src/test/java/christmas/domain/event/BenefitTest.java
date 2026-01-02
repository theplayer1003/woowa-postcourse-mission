package christmas.domain.event;

import static christmas.domain.event.BenefitType.DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.global.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class BenefitTest {

    @Test
    void Benefit_CreateSuccess() {
        final Benefit benefit = new Benefit(DISCOUNT, "평일 할인", 2_023);

        // 필드를 꺼내서 검증
        assertThat(benefit.getBenefitType()).isEqualTo(DISCOUNT);
        assertThat(benefit.getAmount()).isEqualTo(2_023);
        assertThat(benefit.getDiscountAmount()).isEqualTo(2_023);

        // 동등성 구현 후
        assertThat(benefit).isEqualTo(new Benefit(DISCOUNT, "평일 할인", 2_023));
    }

    @Test
    void Benefit_CreateFail_BenefitTypeNull(){
        assertThatThrownBy(() -> new Benefit(null, "평일 할인", 2_023))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("null 일 수 없습니다.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void Benefit_CreateFail_EventNameNullOrBlank(String eventName){
        assertThatThrownBy(() -> new Benefit(DISCOUNT, eventName, 2_023))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("null 이거나 비어 있을 수 없습니다.");
    }

    @Test
    void Benefit_Equality(){
        final Benefit benefit1 = new Benefit(DISCOUNT, "평일 할인", 2_023);
        final Benefit benefit2 = new Benefit(DISCOUNT, "평일 할인", 2_023);

        assertThat(benefit1).isEqualTo(benefit2);
    }

    @Test
    void getAmount() {
        final Benefit discountBenefit = new Benefit(DISCOUNT, "평일 할인", 2_023);
        final Benefit giftBenefit = new Benefit(BenefitType.GIFT, "평일 할인", 2_023);

        assertThat(discountBenefit.getAmount()).isEqualTo(2_023);
        assertThat(giftBenefit.getAmount()).isEqualTo(2_023);
    }

    @Test
    void getDiscountAmount() {
        final Benefit benefit1 = new Benefit(DISCOUNT, "평일 할인", 2_023);
        final Benefit benefit2 = new Benefit(BenefitType.GIFT, "평일 할인", 2_023);

        assertThat(benefit1.getDiscountAmount()).isEqualTo(2_023);
        assertThat(benefit2.getDiscountAmount()).isEqualTo(0);
    }
}