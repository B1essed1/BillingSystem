package shakh.billingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shakh.billingsystem.models.ApiResponse;
import shakh.billingsystem.models.UnloadDto;
import shakh.billingsystem.services.CategoryService;
import shakh.billingsystem.services.UnloadService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/unload")
public class UnloadController {
     private final UnloadService unloadService;
     private final CategoryService categoryService;
    @PostMapping("/unload")
    public ResponseEntity unload(@RequestBody UnloadDto dto){
        unloadService.unloadProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }
    @PostMapping("/new/category")
    public ResponseEntity createNewCategory(@RequestParam String category){
        ApiResponse response = categoryService.createNewCategory(category);
        if (response.getIsError()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(response.getMessage());
        }
    }
}
