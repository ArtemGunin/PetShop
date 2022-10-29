package ua.com.gunin.NIX11.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.gunin.NIX11.dto.UserDTO;
import ua.com.gunin.NIX11.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);

    void save(User user);

    UserDTO findUserById(String id);

    List<UserDTO> getAllUsers();

    boolean deleteUser(String id);

    User findByUsername(String name);

    List<UserDTO> findBySurname(String surname);

    Optional<UserDTO> findByEmail(String email);

    List<UserDTO> findByCity(String city);

    User findFirstByPhoneNumber(String phoneNumber);

    void updateProfile(UserDTO userDTO);

}
