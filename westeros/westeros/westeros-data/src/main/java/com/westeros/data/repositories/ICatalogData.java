package com.westeros.data.repositories;

import com.westeros.data.clases.Address;

import java.util.List;

public interface ICatalogData {
    AddressRepository getAddresses();
    GradeRepository getGrades();
    StudentRepository getStudents();

}
