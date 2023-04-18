package com.company.inventory.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {
    private  static final long serialVersionUID = 1958047171246936454L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

}
