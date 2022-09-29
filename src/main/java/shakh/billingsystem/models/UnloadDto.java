package shakh.billingsystem.models;

import lombok.Data;

@Data
public class UnloadDto {

    private Double amount;

    private Double priceOfBuy;

    private Double priceOfSell;

    private Boolean isActive = true;

    private Boolean measureType;

    private Long productId;

}
