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

    private Boolean isDeleted = false;

    @ManyToOne
    Orders orders;

    @ManyToOne(cascade = CascadeType.PERSIST)
    Debitors debitors;
}
