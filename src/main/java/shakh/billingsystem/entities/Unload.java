package shakh.billingsystem.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Unload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long barcode;

    private Double amount;

    private Double priceOfBuy;

    private Double priceOfSell;

    private Boolean measureType;

    private Boolean isDeleted = false;

    private Date createdTime;

    @ManyToOne(cascade = CascadeType.PERSIST,targetEntity = Products.class )
    Products products;

    @ManyToOne(cascade = CascadeType.PERSIST,targetEntity = Admins.class )
    Admins admin;

    @ManyToOne
    private Company company;
}
