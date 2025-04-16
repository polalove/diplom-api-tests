package data;

import model.Pet;

import java.util.ArrayList;

public class PetDataFactory {
    public static Pet createPet(Long id, String name, String status) {
        Pet pet = new Pet();
        pet.setId(id);
        pet.setName(name);
        pet.setStatus(status);
        pet.setPhotoUrls(new ArrayList<>());
        pet.setTags(new ArrayList<>());
        return pet;
    }
}