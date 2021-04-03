package com.example.springdatarestprojection;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @EqualsAndHashCode.Include
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Person(String name) {
        this.name = name;
    }
}
