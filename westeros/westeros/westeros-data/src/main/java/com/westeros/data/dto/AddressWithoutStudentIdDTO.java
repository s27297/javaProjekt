package com.westeros.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressWithoutStudentIdDTO {
    private Integer id;
    private String city;
    private String postalCode;
    private String street;


}
