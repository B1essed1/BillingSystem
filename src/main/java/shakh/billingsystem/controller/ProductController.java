package shakh.billingsystem.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import shakh.billingsystem.entities.Products;
import shakh.billingsystem.models.ProductCreateDto;
import shakh.billingsystem.services.ProductService;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create/new/")
    public ResponseEntity createProduct(@RequestBody ProductCreateDto dto) throws NotFoundException {
        Products products =  productService.convertToProduct(dto);
        productService.save(products);

        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }


}
