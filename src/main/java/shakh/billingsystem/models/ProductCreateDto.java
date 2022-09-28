package shakh.billingsystem.models;

import lombok.Data;

@Data
public class ProductCreateDto {

    private  Long barcode;
    private  String name;
    private Boolean measureType;
    private Double priceOfBuy;
    private Double priceOfSell;
    private String category;

}
