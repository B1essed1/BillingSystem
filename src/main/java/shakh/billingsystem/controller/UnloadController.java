package shakh.billingsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shakh.billingsystem.models.UnloadDto;
import shakh.billingsystem.services.UnloadService;

@RestController

@RequestMapping("/api/unload")
public class UnloadController {

    @Autowired
     UnloadService unloadService;
    @Autowired
     UnloadService unloadServic;

    @PostMapping("/unload")
    public ResponseEntity unload(@RequestBody UnloadDto dto){
        unloadService.unloadProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

}
