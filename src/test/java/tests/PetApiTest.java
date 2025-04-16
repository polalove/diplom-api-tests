package tests;

import model.Pet;
import model.ApiResult;
import org.junit.jupiter.api.*;
import steps.PetSteps;
import data.PetDataFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("API тесты для работы с питомцами")
public class PetApiTest extends ApiTestBase {

    PetSteps petSteps = new PetSteps();

    @Test
    @DisplayName("Полный цикл работы с питомцем. Создание / получение / удаление / проверка удаления")
    void petLifecycleTest() {
        Pet testPet = PetDataFactory.createPet(
                ThreadLocalRandom.current().nextLong(10000, 99999),
                "Rex",
                "available"
        );
        Pet createdPet = petSteps.createPet(testPet);

        assertThat(createdPet)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(testPet);

        Pet retrievedPet = petSteps.getPetById(createdPet.getId());
        assertThat(retrievedPet).isEqualTo(createdPet);

        ApiResult deleteResponse = petSteps.deletePet(createdPet.getId());
        assertThat(deleteResponse.getCode()).isEqualTo(200);

        ApiResult errorResponse = petSteps.getPetByIdNotFound(createdPet.getId());
        assertThat(errorResponse.getMessage()).isEqualTo("Pet not found");
    }

    @Test
    @DisplayName("Поиск питомцев по статусу")
    void findPetsByStatusTest() {
        List<Pet> pets = petSteps.getPetsByStatus("available");

        assertThat(pets)
                .isNotEmpty()
                .allSatisfy(pet ->
                        assertThat(pet.getStatus()).isEqualTo("available")
                );
    }

    @Test
    @DisplayName("Создание питомца с кастомными данными")
    void createPetWithCustomDataTest() {
        Pet customPet = PetDataFactory.createPet(
                999L,
                "Барсик",
                "pending"
        );

        Pet createdPet = petSteps.createPet(customPet);

        assertThat(createdPet)
                .extracting(Pet::getId, Pet::getName, Pet::getStatus)
                .containsExactly(999L, "Барсик", "pending");
    }
}