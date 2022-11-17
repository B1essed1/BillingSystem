package shakh.billingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import shakh.billingsystem.entities.Debitors;
import shakh.billingsystem.models.DebitorsRegDto;
import shakh.billingsystem.repositories.DebitorsRepository;
import shakh.billingsystem.services.DebitorsService;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/debitors/")
public class DebitorsController {

    private final DebitorsService debitorsService;
    private final DebitorsRepository debitorsRepository;

    @PostMapping("register")
    public ResponseEntity<?> registerDebitors(@RequestBody DebitorsRegDto dto){

        Debitors debitors = debitorsService.registerDebitors(dto);
        debitorsRepository.save(debitors);


        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @GetMapping("debts")
    public ResponseEntity<?> getDebitors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
            ){
        Map<String , Object> response = debitorsService.getDebitors(page,size);
        if (response != null){
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.ok("Qarzdorlar yo'q");
        }

    }
}
