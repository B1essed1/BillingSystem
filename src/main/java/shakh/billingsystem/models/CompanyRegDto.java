package shakh.billingsystem.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyRegDto {

    private String name;

    private String salt;

    private Boolean isActive;

    private Boolean isPayed;

}
