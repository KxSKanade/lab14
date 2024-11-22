package com.tecsup.petclinic.domain;

import lombok.Data;

@Data
public class SpecialtyTO {

    private Integer id;
    private String name;
    private String office;
    private String h_open;
    private String h_close;
}
