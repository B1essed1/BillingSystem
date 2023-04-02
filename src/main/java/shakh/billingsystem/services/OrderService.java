package shakh.billingsystem.services;

import shakh.billingsystem.models.ApiResponse;
import shakh.billingsystem.models.ProductOrderDto;

public interface OrderService {
    ApiResponse sell(ProductOrderDto orderDto);

}
