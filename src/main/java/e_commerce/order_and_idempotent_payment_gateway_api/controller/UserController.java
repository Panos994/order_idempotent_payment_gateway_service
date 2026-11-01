package e_commerce.order_and_idempotent_payment_gateway_api.controller;

import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreateUserRequestDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreateUserResponseDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.entity.Role;
import e_commerce.order_and_idempotent_payment_gateway_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUserRequestDTO));
    }

    @GetMapping
    public ResponseEntity<CreateUserResponseDTO> getUserEmail(@RequestParam String email){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserEmail(email));
    }

    @PatchMapping("{userId}")
    public ResponseEntity<Void> assignRole(@RequestParam Role newRole, @PathVariable UUID userId){
        userService.assignRole(newRole, userId);
        return ResponseEntity.ok().build();
    }
}
