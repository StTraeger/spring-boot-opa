package de.doubleslash.openpolicyageent.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "beer")
public class BeerBE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brewery;
    private String name;
    private double alcohol;
}
