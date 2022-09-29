package shakh.billingsystem.models;

import lombok.Data;

import java.util.Date;

@Data
public class DebitorsRegDto {
    private String name;
    private String lastName;
    private String additionalDetails;
    private String phoneNumber;
    private Double debt;
    private Double deposit;
    private Date createdTime;
    private Double isDeleted;
}
