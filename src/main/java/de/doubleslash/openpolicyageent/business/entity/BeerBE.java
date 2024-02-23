package de.doubleslash.openpolicyageent.business.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "beer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BeerBE {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String brewery;
    private String name;
    private double alcohol;
}
