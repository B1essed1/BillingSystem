package shakh.billingsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
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
    @ManyToOne(cascade = CascadeType.PERSIST, targetEntity = Admins.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "admins_id")
    @JsonBackReference(value = "admin-order")
    Admins admin;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    Debitors debitors;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_payments",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "payment_id") })
    List<Payments> payments = new ArrayList<>();


}
