package e_commerce.order_and_idempotent_payment_gateway_api.service;

import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreateProductRequestDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreateProductResponseDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.entity.Product;
import e_commerce.order_and_idempotent_payment_gateway_api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Transactional
    public CreateProductResponseDTO createProduct(CreateProductRequestDTO createProductRequestDTO){
        Product product = new Product();

        product.setName(createProductRequestDTO.getName());
        product.setDescription(createProductRequestDTO.getDescription());
        if(createProductRequestDTO.getPrice().compareTo(BigDecimal.ZERO)<= 0) throw new IllegalArgumentException("Price must be greater than 0");
        product.setPrice(createProductRequestDTO.getPrice());
        product.setStockQuantity(createProductRequestDTO.getStockQuantity());
        productRepository.save(product);
        return CreateProductResponseDTO.builder()
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .build();
    }

    public List<CreateProductResponseDTO> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(product->CreateProductResponseDTO.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .stockQuantity(product.getStockQuantity())
                        .build()).collect(Collectors.toList());
    }

    public CreateProductResponseDTO getProductById(UUID id){
        return productRepository.findById(id)
                .stream()
                .map(product->CreateProductResponseDTO.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .stockQuantity(product.getStockQuantity())
                        .build()).collect(Collectors.toList()).stream().findFirst().orElse(null);
    }

}
