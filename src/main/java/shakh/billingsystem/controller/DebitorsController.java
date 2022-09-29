package shakh.billingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shakh.billingsystem.entities.Debitors;
import shakh.billingsystem.models.DebitorsRegDto;
import shakh.billingsystem.services.DebitorsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/debitors/")
public class DebitorsController {

    private final DebitorsService debitorsService;

    @PostMapping("register")
    public ResponseEntity<?> registerDebitors(@RequestBody DebitorsRegDto dto){

        Debitors debitors = debitorsService.registerDebitors(dto);


        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }
}
