package e_commerce.order_and_idempotent_payment_gateway_api.service;

import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreateUserRequestDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreateUserResponseDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.entity.Role;
import e_commerce.order_and_idempotent_payment_gateway_api.entity.User;
import e_commerce.order_and_idempotent_payment_gateway_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public CreateUserResponseDTO createUser(CreateUserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("User with email " + dto.getEmail() + " already exists");
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.ROLE_USER);

        userRepository.save(user);
        return CreateUserResponseDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();

    }

    public CreateUserResponseDTO getUserEmail(String email){
        User user = userRepository.findByEmail(email).orElse(null);
        return CreateUserResponseDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
    @Transactional
    public void assignRole(Role newRole, UUID userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("User with id " + userId + " not found"));
        user.setRole(newRole);
    }


}
