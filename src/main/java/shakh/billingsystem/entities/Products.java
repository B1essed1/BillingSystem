package shakh.billingsystem.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long barcode;

    @Size(min = 5, message = "kamida 5 ta belgidan tashkil topgan bo'lishi kerak !")
    private String productName;

    private Double priceOfBuy;

    private Double priceOfSell;

    private Boolean measureType;

    private Boolean isDeleted = false;

    @PositiveOrZero(message = "qiymat doim noldan katta bo'lishi kerak")
    private Double amount;

    private Date createdTime;

    private Date lastUpdatedTime;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "products")
    List<Unload> unloads = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    Category category;

}
