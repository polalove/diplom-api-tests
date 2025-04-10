package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class ApiSpecs {

    public static RequestSpecification requestSpec() {
        return with()
                .filter(withCustomTemplates())
                .log().all()
                .contentType(JSON);
    }

    public static ResponseSpecification responseCod200Spec() {
        return new ResponseSpecBuilder()
                .log(ALL)
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseCod404Spec() {
        return new ResponseSpecBuilder()
                .log(ALL)
                .expectStatusCode(404)
                .build();
    }
}