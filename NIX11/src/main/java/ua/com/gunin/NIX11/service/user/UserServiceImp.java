package ua.com.gunin.NIX11.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.gunin.NIX11.dto.UserDTO;
import ua.com.gunin.NIX11.mapper.UserMapper;
import ua.com.gunin.NIX11.model.User;
import ua.com.gunin.NIX11.model.enums.Role;
import ua.com.gunin.NIX11.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserMapper mapper = UserMapper.MAPPER;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(UserDTO userDTO) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getPasswordConfirm())) {
            throw new RuntimeException("Password is not equals");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setRole(Role.BUYER);
        userRepository.save(mapper.toUser(userDTO));
        return true;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDTO findUserById(String id) {
        return mapper.fromUser(userRepository.findById(id).orElseThrow());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return mapper.fromUserList(userRepository.findAll());
    }

    @Override
    public boolean deleteUser(String id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    @Override
    public List<UserDTO> findBySurname(String surname) {
        return mapper.fromUserList(userRepository.findBySurnameContainingIgnoreCase(surname));
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        return Optional.of(mapper.fromUser(userRepository.findByEmail(email).orElseThrow()));
    }

    @Override
    public List<UserDTO> findByCity(String city) {
        return mapper.fromUserList(userRepository.findByCityIgnoreCase(city));
    }

    @Override
    public User findFirstByPhoneNumber(String phoneNumber) {
        return userRepository.findFirstByPhoneNumber(phoneNumber);
    }

    @Override
    @Transactional
    public void updateProfile(UserDTO userDTO) {
        User savedUser = userRepository.findFirstByUsername(userDTO.getUsername());
        if (savedUser == null) {
            throw new RuntimeException("User not found by username " + userDTO.getUsername());
        }

        boolean isChanged = false;
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            savedUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            isChanged = true;
        }

        if (!Objects.equals(userDTO.getEmail(), savedUser.getEmail())) {
            savedUser.setEmail(userDTO.getEmail());
            isChanged = true;
        }

        if (!Objects.equals(userDTO.getBonus(), savedUser.getBonus())) {
            savedUser.setBonus(userDTO.getBonus());
            isChanged = true;
        }

        if (isChanged) {
            userRepository.save(savedUser);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User: " + username + " - not found.");
        }
        List<GrantedAuthority> userTypes = new ArrayList<>();
        userTypes.add(new SimpleGrantedAuthority(user.getUsername()));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                userTypes
        );
    }
}
