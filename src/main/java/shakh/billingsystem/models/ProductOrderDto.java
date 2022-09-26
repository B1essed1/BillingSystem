package shakh.billingsystem.models;

import lombok.Data;

@Data
public class ProductOrderDto {

    private Double totalCost;
    private Double paidCost;
    private  OrderItemsDto itemsDto;
}
@Data
class OrderItemsDto{
    private Long id;
    private Integer barcode;
    private Double amount;
    private Double priceOfSell;
    private Double priceOfBuy;

}
