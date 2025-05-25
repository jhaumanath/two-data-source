package com.umanath.twodatasource.app;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "my_entity")
public class MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;

    @Version
    private Long version;

}
