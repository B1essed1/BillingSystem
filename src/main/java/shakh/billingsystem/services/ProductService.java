package shakh.billingsystem.services;


import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import shakh.billingsystem.entities.Products;
import shakh.billingsystem.models.ApiResponse;
import shakh.billingsystem.models.ProductCreateDto;

public interface ProductService {
    Products findProductById(Long id);
    Products save(Products products);

    ApiResponse<?> convertToProduct(ProductCreateDto dto) throws NotFoundException, javassist.NotFoundException;
}
