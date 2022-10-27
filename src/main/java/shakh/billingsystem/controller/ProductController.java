package shakh.billingsystem.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import shakh.billingsystem.entities.Products;
import shakh.billingsystem.models.ProductCreateDto;
import shakh.billingsystem.models.ProductNameDto;
import shakh.billingsystem.repositories.ProductElRepository;
import shakh.billingsystem.services.ProductService;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductElRepository productElRepository;

    @PostMapping("/create/new")
    public ResponseEntity createProduct(@RequestBody ProductCreateDto dto){
        try {
            Products products = productService.convertToProduct(dto);
            Products prod= productService.save(products);

          productElRepository.save(ProductNameDto.builder()
                  .name(prod.getProductName())
                  .id(prod.getId()).build());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @GetMapping("/get/product")
    public ResponseEntity getProduct(@RequestParam(name = "name") String name){

        return ResponseEntity.ok(productElRepository.findByName(name));
    }

}
