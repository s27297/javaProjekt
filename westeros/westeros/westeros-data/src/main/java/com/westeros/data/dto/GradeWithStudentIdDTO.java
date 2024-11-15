package com.westeros.data.dto;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class GradeWithStudentIdDTO {
        private  Integer id;
        private  String subject;
        private  String grade;
        private Integer studentId;

    }
