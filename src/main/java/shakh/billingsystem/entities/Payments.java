package shakh.billingsystem.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Payments {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double payment;
    private Boolean isDeleted = false;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Orders orders;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private   Debitors debitors;

    @ManyToOne
    private Admins admins;
}
