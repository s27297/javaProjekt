package com.westeros.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GradeWithoutStudentIDDTO {
    private  Integer id;
    private  String subject;
    private  String grade;



}
