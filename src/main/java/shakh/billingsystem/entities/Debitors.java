package shakh.billingsystem.entities;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Debitors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String additionalDetail;
    @Column(unique = true)
    private String phoneNumber;
    private Date createdDate;

    private Double debt = 0.0;
    private Double deposit= 0.0;
    private Boolean isDeleted = false;

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "debitors", fetch = FetchType.LAZY)
    List<Orders> orders = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "debitors", fetch = FetchType.LAZY)
    List<Payments> payments = new ArrayList<>();

    @ManyToOne
    private Company company;
}
