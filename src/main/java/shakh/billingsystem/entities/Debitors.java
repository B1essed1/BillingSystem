package shakh.billingsystem.entities;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Debitors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String additionalDetail;
    private String phoneNumber;
    private Date createdDate;
    private Double debt;
    private Double deposit;
    private Boolean isDeleted = false;

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "debitors")
    List<Orders> orders = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "debitors")
    List<Payments> payments = new ArrayList<>();
}
