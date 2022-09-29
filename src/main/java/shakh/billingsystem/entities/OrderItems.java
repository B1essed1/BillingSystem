package shakh.billingsystem.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isDeleted = false;

    private Double amount;

    private String productName;

    private Double priceOfSell;

    private Double priceOfBuy;

    private Date createdTime;

    private String category;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Admins admins;
    @ManyToOne(cascade = CascadeType.PERSIST)
    Orders orders;

}
