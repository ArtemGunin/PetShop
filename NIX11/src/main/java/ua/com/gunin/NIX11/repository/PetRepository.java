package ua.com.gunin.NIX11.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.gunin.NIX11.model.Pet;

public interface PetRepository extends CrudRepository<Pet, String> {
}
