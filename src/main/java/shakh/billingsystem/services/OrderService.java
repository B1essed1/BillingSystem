package shakh.billingsystem.services;

import javassist.NotFoundException;
import shakh.billingsystem.models.CustomResponseDto;
import shakh.billingsystem.models.ProductOrderDto;

public interface OrderService {
    CustomResponseDto sell(ProductOrderDto orderDto);

}
