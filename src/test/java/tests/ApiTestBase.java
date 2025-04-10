package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class ApiTestBase {

    @AfterEach
    void delay() throws InterruptedException {
        Thread.sleep(500); // 0.5 сек пауза между тестами
    }

    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }
}
