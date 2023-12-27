package de.doubleslash.openpolicyageent.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.annotation.processing.Generated;

@Data
@Entity
@Table(name = "beer")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brewery;
    private String name;
    private double alcohol;
    private long amount;
}
