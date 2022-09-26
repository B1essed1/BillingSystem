package shakh.billingsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shakh.billingsystem.models.ProductOrderDto;

@RestController
@RequestMapping("/api/orders/")
public class OrderController {

    @PostMapping("sell")
    public ResponseEntity sell(@RequestBody ProductOrderDto dto){
        /** TO DO
         *
         * implement selling process
         *
         * **/



        return  ResponseEntity.ok().body("sold");
    }

}
