package shakh.billingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shakh.billingsystem.models.CustomResponseDto;
import shakh.billingsystem.models.ProductOrderDto;
import shakh.billingsystem.services.OrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders/")
public class OrderController {

    private final OrderService orderService;
    @PostMapping("sell")
    public ResponseEntity sell(@RequestBody ProductOrderDto dto){
        /** TO DO
         * implement selling process
         **/
        CustomResponseDto response = orderService.sell(dto);
        if (response.getIsError()==true) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response.getMessage());
        }
        return  ResponseEntity.ok().body("sold");
    }

}
