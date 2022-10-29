package ua.com.gunin.NIX11.mapper;

import ua.com.gunin.NIX11.dto.PetDTO;
import ua.com.gunin.NIX11.model.Pet;

public final class PetMapper {

    public static PetDTO mapPetToDTO(Pet pet) {
        return new PetDTO(
                pet.getId(),
                pet.getName(),
                pet.getUser(),
                pet.getPetType()
        );
    }
}
