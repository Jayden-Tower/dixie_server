package com.example.dixie.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "productName")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private Categories category;

    @Column(name = "price")
    private String price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    private String status;


}
