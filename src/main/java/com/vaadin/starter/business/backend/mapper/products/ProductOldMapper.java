package com.vaadin.starter.business.backend.mapper.products;

import com.vaadin.starter.business.dummy.Product;
import com.vaadin.starter.business.backend.dto.products.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Product and ProductDTO objects.
 */
@Component
public class ProductOldMapper {

    /**
     * Convert a Product entity to a ProductDTO.
     *
     * @param product the Product entity to convert
     * @return the corresponding ProductDTO
     */
    public ProductDTO toDto(Product product) {
        if (product == null) {
            return null;
        }
        
        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getCategory(),
            product.getDescription(),
            product.getPrice(),
            product.isActive(),
            product.getCreatedDate()
        );
    }

    /**
     * Convert a ProductDTO to a Product entity.
     *
     * @param dto the ProductDTO to convert
     * @return the corresponding Product entity
     */
    public Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new Product(
            dto.getId(),
            dto.getName(),
            dto.getCategory(),
            dto.getDescription(),
            dto.getPrice(),
            dto.isActive(),
            dto.getCreatedDate()
        );
    }

    /**
     * Convert a collection of Product entities to a list of ProductDTOs.
     *
     * @param products the collection of Product entities to convert
     * @return a list of corresponding ProductDTOs
     */
    public List<ProductDTO> toDtoList(Collection<Product> products) {
        if (products == null) {
            return List.of();
        }
        
        return products.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of ProductDTOs to a list of Product entities.
     *
     * @param dtos the collection of ProductDTOs to convert
     * @return a list of corresponding Product entities
     */
    public List<Product> toEntityList(Collection<ProductDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}