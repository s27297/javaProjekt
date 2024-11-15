package com.westeros.data.dto;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    @OneToMany
    List<AddressWithStudentIdDTO> addressesDTOS;
    @OneToMany
    List<GradeWithStudentIdDTO> grades;
}
