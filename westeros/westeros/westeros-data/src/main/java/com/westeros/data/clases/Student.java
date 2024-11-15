package com.westeros.data.clases;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Address> addresses=new ArrayList<>();
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Grade> grades=new ArrayList<>();

    public Student(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }




}
