package shakh.billingsystem.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter

public class Payments {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double payment;
    private Boolean isDeleted = false;
    private  Date createdDate ;
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY,mappedBy = "payments")
    private List<Orders> orders = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private   Debitors debitors;

    @ManyToOne
    private Admins admins;
}
