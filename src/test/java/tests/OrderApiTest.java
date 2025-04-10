package tests;

import model.ApiResult;
import model.Order;
import org.junit.jupiter.api.*;
import services.OrderApiService;
import data.OrderDataFactory;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderApiTest extends ApiTestBase {

    private static final OrderApiService storeService = new OrderApiService();
    private static Long createdOrderId;
    private static Order testOrder;

    @BeforeAll
    static void setUp() {
        testOrder = OrderDataFactory.generateTestOrder();
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    @DisplayName("Создание нового заказа")
    void createOrderTest() {
        Order createdOrder = storeService.createOrder(testOrder);
        createdOrderId = createdOrder.getId();

        assertThat(createdOrder.getId()).isEqualTo(testOrder.getId());
        assertThat(createdOrder.getStatus()).isEqualTo(testOrder.getStatus());
        assertThat(createdOrder.getPetId()).isEqualTo(testOrder.getPetId());
        assertThat(createdOrder.getQuantity()).isEqualTo(testOrder.getQuantity());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    @DisplayName("Получение созданного заказа")
    void getCreatedOrderTest() {
        Order retrievedOrder = storeService.getOrder(createdOrderId);

        assertThat(retrievedOrder)
                .usingRecursiveComparison()
                .ignoringFields("shipDate")
                .isEqualTo(testOrder);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    @DisplayName("Удаление заказа")
    void deleteOrderTest() {
        ApiResult deleteResponse = storeService.deleteOrder(createdOrderId);
        assertThat(deleteResponse.getCode()).isEqualTo(200);
        assertThat(deleteResponse.getMessage()).isEqualTo(createdOrderId.toString());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    @DisplayName("Попытка получить удаленный заказ")
    void getDeletedOrderTest() {
        ApiResult errorResponse = storeService.getOrderExpectingError(createdOrderId);
        assertThat(errorResponse.getCode()).isEqualTo(1);
        assertThat(errorResponse.getMessage()).isEqualTo("Order not found");
    }
}