package shakh.billingsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalCost;
    private Double paidCost;
    private Date createdTime;
    private Boolean isDeleted = false;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "orders", fetch = FetchType.LAZY)
    List<OrderItems> orderItems = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.PERSIST, targetEntity = Admins.class)
    @JoinColumn(name = "admins_id")
    @JsonBackReference(value = "admin-order")
    Admins admin;

    @ManyToOne(cascade = CascadeType.PERSIST)
    Debitors debitors;

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "orders")
    List<Payments> payments = new ArrayList<>();


}
