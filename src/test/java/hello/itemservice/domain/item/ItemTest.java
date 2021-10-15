package hello.itemservice.domain.item;

import hello.itemservice.web.validation.Errors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

    @Test
    @DisplayName("validItem - 입력이 모두 null인 경우")
    public void 모든입력_null() {
        // given
        String itemName = null;
        Integer price = null;
        Integer quantity = null;
        Item item = new Item(itemName, price, quantity);

        // when
        Errors errors = item.validItem();
        Map<String, String> errorMap = errors.errors();

        // then
        assertThat(errors.hasErrors()).isTrue();
        assertThat(errorMap.containsKey("itemName")).isTrue();
        assertThat(errorMap.containsKey("price")).isTrue();
        assertThat(errorMap.containsKey("quantity")).isTrue();
    }

    @ParameterizedTest(name = "validItem - 부적절한 price 입력 | {arguments}")
    @ValueSource(ints = {999, 1000001})
    public void 부적절한_price(Integer price) {
        // given
        String itemName = "test";
        int quantity = 100;
        Item item = new Item(itemName, price, quantity);

        // when
        Errors errors = item.validItem();
        Map<String, String> errorMap = errors.errors();

        // then
        assertThat(errors.hasErrors()).isTrue();
        assertThat(errorMap.containsKey("itemName")).isFalse();
        assertThat(errorMap.containsKey("price")).isTrue();
        assertThat(errorMap.containsKey("quantity")).isFalse();
    }

    @ParameterizedTest(name = "validItem - 부적절한 quantity 입력 | {arguments}")
    @ValueSource(ints = {10000})
    public void 부적절한_quantity(Integer quantity) {
        // given
        String itemName = "test";
        int price = 1000;
        Item item = new Item(itemName, price, quantity);

        // when
        Errors errors = item.validItem();
        Map<String, String> errorMap = errors.errors();

        // then
        assertThat(errors.hasErrors()).isTrue();
        assertThat(errorMap.containsKey("itemName")).isFalse();
        assertThat(errorMap.containsKey("price")).isFalse();
        assertThat(errorMap.containsKey("quantity")).isTrue();
    }

    @Test
    @DisplayName("validItem - 총 가격 (가격*양) 이 10,000원 보다 적은 경우")
    public void 부적절한_총가격() {
        // given
        String itemName = "test";
        Integer price = 1000;
        Integer quantity = 1;
        Item item = new Item(itemName, price, quantity);

        // when
        Errors errors = item.validItem();
        Map<String, String> errorMap = errors.errors();

        // then
        assertThat(errors.hasErrors()).isTrue();
        assertThat(errorMap.containsKey("globalError")).isTrue();
        assertThat(errorMap.containsKey("itemName")).isFalse();
        assertThat(errorMap.containsKey("price")).isFalse();
        assertThat(errorMap.containsKey("quantity")).isFalse();
    }

    @Test
    @DisplayName("validItem - 적절한 입력")
    public void 적절한_입력() {
        // given
        String itemName = "test";
        Integer price = 1000;
        Integer quantity = 100;
        Item item = new Item(itemName, price, quantity);

        // when
        Errors errors = item.validItem();

        // then
        assertThat(errors.hasErrors()).isFalse();
    }

}