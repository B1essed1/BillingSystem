package shakh.billingsystem.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DebitorsDto {
    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private Double debt;
    private String additionalDetail;
}
