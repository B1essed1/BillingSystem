package shakh.billingsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shakh.billingsystem.entities.Products;
import shakh.billingsystem.models.ApiResponse;
import shakh.billingsystem.models.ProductCreateDto;
import shakh.billingsystem.services.ProductService;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create/new")
    public ResponseEntity createProduct(@RequestBody ProductCreateDto dto){
        try {
            ApiResponse response = productService.convertToProduct(dto);
            if (!response.getIsError()){
                Products prod= productService.save((Products) response.getData());
            } else return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response.getMessage());


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

}
