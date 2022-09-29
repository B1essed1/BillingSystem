package shakh.billingsystem.models;

import lombok.Data;

import java.util.List;

@Data
public class ProductOrderDto {

    private Double totalCost;
    private Double paidCost;
    private Long debitorsId;
    private List<OrderItemsDto> itemsDto;
}
