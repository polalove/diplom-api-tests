package steps;

import model.ApiResult;
import model.Order;
import io.qameta.allure.Step;
import specs.ApiSpecs;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Создание заказа")
    public Order createOrder(Order order) {
        return given()
                .spec(ApiSpecs.requestSpec())
                .body(order)
                .when()
                .post("/store/order")
                .then()
                .spec(ApiSpecs.responseCod200Spec())
                .extract().as(Order.class);
    }

    @Step("Получение заказа по ID: {orderId}")
    public Order getOrder(Long orderId) {
        return given()
                .spec(ApiSpecs.requestSpec())
                .when()
                .get("/store/order/{orderId}", orderId)
                .then()
                .spec(ApiSpecs.responseCod200Spec())
                .extract().as(Order.class);
    }

    @Step("Удаление заказа по ID: {orderId}")
    public ApiResult deleteOrder(Long orderId) {
        return given()
                .spec(ApiSpecs.requestSpec())
                .header("api_key", "special-key")
                .when()
                .delete("/store/order/{orderId}", orderId)
                .then()
                .spec(ApiSpecs.responseCod200Spec())
                .extract().as(ApiResult.class);
    }

    @Step("Получение ошибки при запросе заказа по ID: {orderId}")
    public ApiResult getOrderExpectingError(Long orderId) {
        return given()
                .spec(ApiSpecs.requestSpec())
                .when()
                .get("/store/order/{orderId}", orderId)
                .then()
                .spec(ApiSpecs.responseCod404Spec())
                .extract().as(ApiResult.class);
    }
}