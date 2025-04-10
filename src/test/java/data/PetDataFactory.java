package data;

import model.Pet;

public class PetDataFactory {
    public static final Long DEFAULT_PET_ID = 12345L;
    public static final String DEFAULT_PET_NAME = "Fluffy";
    public static final String DEFAULT_PET_STATUS = "available";


    public static Pet createDefaultPet() {
        return createPet(DEFAULT_PET_ID, DEFAULT_PET_NAME, DEFAULT_PET_STATUS);
    }

    public static Pet createPet(Long id, String name, String status) {
        Pet pet = new Pet();
        pet.setId(id);
        pet.setName(name);
        pet.setStatus(status);
        return pet;
    }

}