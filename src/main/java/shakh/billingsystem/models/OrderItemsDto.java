package shakh.billingsystem.models;

import lombok.Data;

@Data
public class OrderItemsDto {
    private Long productId;
    private String productName;
    private Integer barcode;
    private Double amount;
    private Double priceOfSell;
    private Double priceOfBuy;
    private String category;

}
