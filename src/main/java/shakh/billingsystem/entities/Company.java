package shakh.billingsystem.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private Date createdTime;

    @Column(nullable = false)
    private String salt;

    private Boolean isActive = true;

    private Boolean isPayed =  true  ;

    private Date paymentTime;
}
