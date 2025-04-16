package tests;

import model.ApiResult;
import model.Order;
import org.junit.jupiter.api.*;
import steps.OrderSteps;
import data.OrderDataFactory;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("API тесты для работы с заказами")
public class OrderApiTest extends ApiTestBase {

    OrderSteps orderSteps = new OrderSteps();
    private Long testOrderId;
    private Order testOrder;

    @BeforeEach
    void setUp() {
        testOrderId = ThreadLocalRandom.current().nextLong(10000, 99999);
        testOrder = OrderDataFactory.createTestOrder(testOrderId);
    }

    @Test
    @DisplayName("Создание и получение заказа")
    void createAndGetOrderTest() {
        Order createdOrder = orderSteps.createOrder(testOrder);

        assertThat(createdOrder)
                .usingRecursiveComparison()
                .ignoringFields("shipDate")
                .isEqualTo(testOrder);

        Order retrievedOrder = orderSteps.getOrder(createdOrder.getId());
        assertThat(retrievedOrder)
                .usingRecursiveComparison()
                .ignoringFields("shipDate")
                .isEqualTo(createdOrder);
    }

    @Test
    @DisplayName("Удаление заказа")
    void deleteOrderTest() {
        Order createdOrder = orderSteps.createOrder(testOrder);
        ApiResult deleteResponse = orderSteps.deleteOrder(createdOrder.getId());

        assertThat(deleteResponse)
                .extracting(ApiResult::getCode, ApiResult::getMessage)
                .containsExactly(200, createdOrder.getId().toString());
    }

    @Test
    @DisplayName("Получение несуществующего заказа")
    void getNonExistentOrderTest() {
        Long nonExistentId = testOrderId + 1;
        ApiResult errorResponse = orderSteps.getOrderExpectingError(nonExistentId);

        assertThat(errorResponse)
                .extracting(ApiResult::getCode, ApiResult::getMessage)
                .containsExactly(1, "Order not found");
    }
}