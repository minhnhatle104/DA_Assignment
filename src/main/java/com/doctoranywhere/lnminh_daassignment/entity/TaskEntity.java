package com.doctoranywhere.lnminh_daassignment.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "task")
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "completed")
    private Boolean completed;
}
