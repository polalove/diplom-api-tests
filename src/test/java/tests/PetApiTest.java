package tests;

import model.Pet;
import model.ApiResult;
import org.junit.jupiter.api.*;
import services.PetApiService;
import data.PetDataFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("API тесты для работы с питомцами")
public class PetApiTest extends ApiTestBase {

    private static final PetApiService petService = new PetApiService();
    private static Long createdPetId;

    @Test
    @Order(1)
    @DisplayName("Создание нового питомца")
    void shouldCreatePetSuccessfully() {
        Pet testPet = PetDataFactory.createDefaultPet();
        Pet createdPet = petService.createPet(testPet);
        createdPetId = createdPet.getId();

        assertThat(createdPet)
                .isNotNull()
                .satisfies(pet -> {
                    assertThat(pet.getId()).isEqualTo(PetDataFactory.DEFAULT_PET_ID);
                    assertThat(pet.getName()).isEqualTo(PetDataFactory.DEFAULT_PET_NAME);
                    assertThat(pet.getStatus()).isEqualTo(PetDataFactory.DEFAULT_PET_STATUS);
                });
    }

    @Test
    @Order(2)
    @DisplayName("Получение созданного питомца")
    void shouldGetPetByIdSuccessfully() {
        Pet retrievedPet = petService.getPetById(createdPetId);

        assertThat(retrievedPet)
                .isNotNull()
                .satisfies(pet -> {
                    assertThat(pet.getId()).isEqualTo(createdPetId);
                    assertThat(pet.getName()).isEqualTo(PetDataFactory.DEFAULT_PET_NAME);
                });
    }

    @Test
    @Order(3)
    @DisplayName("Поиск по статусу")
    void shouldFindPetsByStatus() {
        List<Pet> pets = petService.getPetsByStatus(PetDataFactory.DEFAULT_PET_STATUS);

        assertThat(pets)
                .isNotEmpty()
                .allSatisfy(pet ->
                        assertThat(pet.getStatus()).isEqualTo(PetDataFactory.DEFAULT_PET_STATUS)
                );
    }

    @Test
    @Order(4)
    @DisplayName("Удаление питомца")
    void shouldDeletePetSuccessfully() {
        ApiResult deleteResponse = petService.deletePet(createdPetId);

        assertThat(deleteResponse)
                .isNotNull()
                .satisfies(response -> {
                    assertThat(response.getCode()).isEqualTo(200);
                    assertThat(response.getMessage()).isEqualTo(createdPetId.toString());
                });
    }

    @Test
    @Order(5)
    @DisplayName("Питомец не найден")
    void shouldReturnNotFoundForDeletedPet() {
        ApiResult errorResponse = petService.getPetByIdNotFound(createdPetId);

        assertThat(errorResponse)
                .isNotNull()
                .satisfies(response -> {
                    assertThat(response.getCode()).isEqualTo(1);
                    assertThat(response.getMessage()).isEqualTo("Pet not found");
                });
    }

    @Test
    @Order(6)
    @DisplayName("Создание питомца с кастомными данными")
    void shouldCreatePetWithCustomData() {
        Pet customPet = PetDataFactory.createPet(999L, "Барсик", "pending");
        Pet createdPet = petService.createPet(customPet);

        assertThat(createdPet)
                .isNotNull()
                .satisfies(pet -> {
                    assertThat(pet.getId()).isEqualTo(999L);
                    assertThat(pet.getName()).isEqualTo("Барсик");
                    assertThat(pet.getStatus()).isEqualTo("pending");
                });
    }
}