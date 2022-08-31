package shakh.billingsystem.models;

import lombok.Data;

@Data
public class ConfirmDto {
    private String email;
    private Integer otp;
}
