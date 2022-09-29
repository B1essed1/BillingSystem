package shakh.billingsystem.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomResponseDto<T>{

    private Boolean isError;
    private String message;
    private T data;
}
