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
    public class AddressWithStudentIdDTO {

        private Integer id;
        private String city;
        private String postalCode;
        private String street;

        private Integer studentId;


    }
