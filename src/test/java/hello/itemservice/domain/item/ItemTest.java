package hello.itemservice.domain.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

//    @Test
//    @DisplayName("validItem - 입력이 모두 null인 경우")
//    public void 모든입력_null() {
//        // given
//        String itemName = null;
//        Integer price = null;
//        Integer quantity = null;
//        Item item = new Item(itemName, price, quantity);
//
//        // when
//        Errors errors = item.validItem();
//        Map<String, String> errorMap = errors.errors();
//
//        // then
//        assertThat(errors.hasErrors()).isTrue();
//        assertThat(errorMap.containsKey("itemName")).isTrue();
//        assertThat(errorMap.containsKey("price")).isTrue();
//        assertThat(errorMap.containsKey("quantity")).isTrue();
//    }
//
//    @ParameterizedTest(name = "validItem - 부적절한 price 입력 | {arguments}")
//    @ValueSource(ints = {999, 1000001})
//    public void 부적절한_price(Integer price) {
//        // given
//        String itemName = "test";
//        int quantity = 100;
//        Item item = new Item(itemName, price, quantity);
//
//        // when
//        Errors errors = item.validItem();
//        Map<String, String> errorMap = errors.errors();
//
//        // then
//        assertThat(errors.hasErrors()).isTrue();
//        assertThat(errorMap.containsKey("itemName")).isFalse();
//        assertThat(errorMap.containsKey("price")).isTrue();
//        assertThat(errorMap.containsKey("quantity")).isFalse();
//    }
//
//    @ParameterizedTest(name = "validItem - 부적절한 quantity 입력 | {arguments}")
//    @ValueSource(ints = {10000})
//    public void 부적절한_quantity(Integer quantity) {
//        // given
//        String itemName = "test";
//        int price = 1000;
//        Item item = new Item(itemName, price, quantity);
//
//        // when
//        Errors errors = item.validItem();
//        Map<String, String> errorMap = errors.errors();
//
//        // then
//        assertThat(errors.hasErrors()).isTrue();
//        assertThat(errorMap.containsKey("itemName")).isFalse();
//        assertThat(errorMap.containsKey("price")).isFalse();
//        assertThat(errorMap.containsKey("quantity")).isTrue();
//    }
//
//    @Test
//    @DisplayName("validItem - 총 가격 (가격*양) 이 10,000원 보다 적은 경우")
//    public void 부적절한_총가격() {
//        // given
//        String itemName = "test";
//        Integer price = 1000;
//        Integer quantity = 1;
//        Item item = new Item(itemName, price, quantity);
//
//        // when
//        Errors errors = item.validItem();
//        Map<String, String> errorMap = errors.errors();
//
//        // then
//        assertThat(errors.hasErrors()).isTrue();
//        assertThat(errorMap.containsKey("globalError")).isTrue();
//        assertThat(errorMap.containsKey("itemName")).isFalse();
//        assertThat(errorMap.containsKey("price")).isFalse();
//        assertThat(errorMap.containsKey("quantity")).isFalse();
//    }
//
//    @Test
//    @DisplayName("validItem - 적절한 입력")
//    public void 적절한_입력() {
//        // given
//        String itemName = "test";
//        Integer price = 1000;
//        Integer quantity = 100;
//        Item item = new Item(itemName, price, quantity);
//
//        // when
//        Errors errors = item.validItem();
//
//        // then
//        assertThat(errors.hasErrors()).isFalse();
//    }

    @Test
    @DisplayName("totalPrice")
    public void totalPrice() {
        // given
        Item item = new Item("test", 1234, 100);

        // when
        int totalPrice = item.totalPrice();

        // then
        assertThat(totalPrice).isEqualTo(123400);
    }

    @Test
    @DisplayName("아이템 이름 - 부적절한 입력")
    public void 아이템이름_부적절한입력() {
        // given
        Item item = new Item(null, 1234, 100);

        // when
        boolean validation = item.isValidItemName();

        // then
        assertThat(validation).isFalse();
    }

    @ParameterizedTest(name = "아이템 가격 - 부적절한 입력 | {arguments}")
    @ValueSource(ints = {999, 1000001})
    public void 아이템가격_부적절한입력(Integer price) {
        // given
        Item item = new Item("test", price, 100);

        // when
        boolean validation = item.isValidPrice();

        // then
        assertThat(validation).isFalse();
    }

    @ParameterizedTest(name = "아이템 개수 - 부적절한 입력 | {arguments}")
    @ValueSource(ints = {10000})
    public void 아이템개수_부적절한입력(Integer quantity) {
        // given
        Item item = new Item("test", 1234, quantity);

        // when
        boolean validation = item.isValidQuantity();

        // then
        assertThat(validation).isFalse();
    }

    @DisplayName("총 가격 (가격*양) 이 10,000원 보다 적은 경우")
    @Test
    public void 아이템_양_개수_부적절한입력() {
        // given
        Item item = new Item("test", 1234, 1);

        // when
        boolean validation = item.isInValidTotalPrice();

        // then
        assertThat(validation).isTrue();
    }

    @DisplayName("적절한 아이템 입력")
    @Test
    public void 아이템생성() {
        // given
        Item item = new Item("test", 1234, 100);

        // when

        // then
        assertThat(item.isValidItemName()).isTrue();
        assertThat(item.isValidPrice()).isTrue();
        assertThat(item.isValidQuantity()).isTrue();
        assertThat(item.isInValidTotalPrice()).isFalse();
    }

}