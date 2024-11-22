package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "specialties")
@Data
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "office")
    private String office;

    @Column(name = "h_open")
    private String h_open;

    @Column(name = "h_close")
    private String h_close;

    public Specialty() {
    }

    public Specialty(String name, String office, String h_open, String h_close) {
        this.name = name;
        this.office = office;
        this.h_open = h_open;
        this.h_close = h_close;
    }
}
