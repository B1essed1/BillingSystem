package shakh.billingsystem.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductNameDto {
    private String name;
    private Long id;
}
