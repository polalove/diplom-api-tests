package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ApiTestBase {
    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }
}