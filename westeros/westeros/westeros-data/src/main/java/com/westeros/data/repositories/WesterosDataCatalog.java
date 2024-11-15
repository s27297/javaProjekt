package com.westeros.data.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class WesterosDataCatalog implements ICatalogData {
    private AddressRepository addressRepository;
    private GradeRepository gradeRepository;

    public WesterosDataCatalog(AddressRepository addressRepository, GradeRepository gradeRepository, StudentRepository studentRepository) {
        this.addressRepository = addressRepository;
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
    }

    private StudentRepository studentRepository;
    @Override
    public AddressRepository getAddresses() {
        return addressRepository;
    }

    @Override
    public GradeRepository getGrades() {
        return gradeRepository;
    }

    @Override
    public StudentRepository getStudents() {
        return studentRepository;
    }
}

