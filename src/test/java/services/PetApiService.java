package services;

import model.Pet;
import model.ApiResult;
import specs.ApiSpecs;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetApiService {

    public Pet createPet(Pet pet) {
        return given()
                .spec(ApiSpecs.requestSpec())
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .spec(ApiSpecs.responseCod200Spec())
                .extract()
                .as(Pet.class);
    }

    public Pet getPetById(Long petId) {
        return given()
                .spec(ApiSpecs.requestSpec())
                .when()
                .get("/pet/{petId}", petId)
                .then()
                .spec(ApiSpecs.responseCod200Spec())
                .extract()
                .as(Pet.class);
    }

    public List<Pet> getPetsByStatus(String status) {
        return given()
                .spec(ApiSpecs.requestSpec())
                .queryParam("status", status)
                .when()
                .get("/pet/findByStatus")
                .then()
                .spec(ApiSpecs.responseCod200Spec())
                .extract()
                .jsonPath()
                .getList("", Pet.class);
    }

    public ApiResult deletePet(Long petId) {
        return given()
                .spec(ApiSpecs.requestSpec())
                .header("api_key", "special-key")
                .when()
                .delete("/pet/{petId}", petId)
                .then()
                .spec(ApiSpecs.responseCod200Spec())
                .extract()
                .as(ApiResult.class);
    }

    public ApiResult getPetByIdNotFound(Long petId) {
        return given()
                .spec(ApiSpecs.requestSpec())
                .when()
                .get("/pet/{petId}", petId)
                .then()
                .spec(ApiSpecs.responseCod404Spec())
                .extract()
                .as(ApiResult.class);
    }
}