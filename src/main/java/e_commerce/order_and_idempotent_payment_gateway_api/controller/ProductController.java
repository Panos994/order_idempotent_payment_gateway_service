package e_commerce.order_and_idempotent_payment_gateway_api.controller;

import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreateProductRequestDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreateProductResponseDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<CreateProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(dto));
    }

    @GetMapping
    public ResponseEntity<List<CreateProductResponseDTO>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("{productId}")
    public ResponseEntity<CreateProductResponseDTO> getProductById(@PathVariable UUID productId){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(productId));
    }
}
