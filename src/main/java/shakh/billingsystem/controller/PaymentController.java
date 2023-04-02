package shakh.billingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shakh.billingsystem.models.ApiResponse;
import shakh.billingsystem.models.PaymentsDto;
import shakh.billingsystem.services.PaymentsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentsService paymentsService;
    @PostMapping("/debts")
    public ResponseEntity payingDebts(@RequestBody PaymentsDto payment){

        ApiResponse response= paymentsService.payingDebts(payment);
    return ResponseEntity.ok().body(response.getMessage());
    }
}
