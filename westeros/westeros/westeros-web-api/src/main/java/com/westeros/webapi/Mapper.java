package com.westeros.webapi;

import com.westeros.data.clases.Address;
import com.westeros.data.clases.Grade;
import com.westeros.data.clases.Student;
import com.westeros.data.dto.*;
import com.westeros.data.repositories.ICatalogData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public  class  Mapper {
   public final ICatalogData db;
    public Mapper(ICatalogData data)
    {this.db=data;}
    public StudentSummaryDTO getSummaryStudentDTO(Student student){
        var dto=new StudentSummaryDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        return dto;
    }


    public Student getStudent(StudentDTO student){
        var dto=new Student();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        List<Address>addresses=new ArrayList<>();
        for (AddressWithStudentIdDTO address: student.getAddressesDTOS()
             ) {
            addresses.add(db.getAddresses().findById(address.getId()).stream().toList().get(0));
        }
        List<Grade>grades=new ArrayList<>();

        for (GradeWithStudentIdDTO grade: student.getGrades()
        ) {
            grades.add(db.getGrades().findById(grade.getId()).stream().toList().get(0));
        }

        dto.setAddresses(addresses);
        dto.setGrades(grades);
        return dto;
    }

    public Student getStudent(StudentSummaryDTO student){
        var dto=new Student();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setAddresses(new ArrayList<>());
        dto.setGrades(new ArrayList<>());
        return dto;
    }

    public Student getSummaryStudent(StudentSummaryDTO student){
        var dto=new Student();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        return dto;
    }
    public GradeWithoutStudentIDDTO getGradeDTO(Grade grade){
        var dto=new GradeWithoutStudentIDDTO();
        dto.setId(grade.getId());
        dto.setGrade(grade.getGrade());
        dto.setSubject(grade.getSubject());
        return dto;
    }

    public GradeWithStudentIdDTO getGradeWithIdDTO(Grade grade){
        var dto=new GradeWithStudentIdDTO();
        dto.setId(grade.getId());
        dto.setGrade(grade.getGrade());
        dto.setSubject(grade.getSubject());
        dto.setStudentId(grade.getStudent().getId());
        return dto;
    }

    public Grade getGrade(GradeWithStudentIdDTO grade){
        var dto=new Grade();
        dto.setId(grade.getId());
        dto.setGrade(grade.getGrade());
        dto.setStudent(db.getStudents().findById(grade.getStudentId()).stream().toList().get(0));
        dto.setSubject(grade.getSubject());
        return dto;
    }



  // public Grade getGrade(GradeWithoutStudentIDDTO grade){
  //     var dto=new Grade();
  //     dto.setId(grade.getId());
  //     dto.setGrade(grade.getGrade());
  //     dto.setStudent(db.getStudents().findById(grade.getStudent().getId()).stream().toList().get(0));
  //     dto.setSubject(grade.getSubject());
  //     return dto;
  // }
    public AddressWithStudentIdDTO getAddressDTO(Address address){
        var dto=new AddressWithStudentIdDTO();
        dto.setId(address.getId());
        dto.setCity(address.getCity());
        dto.setStreet(address.getStreet());
        dto.setPostalCode(address.getPostalCode());
        dto.setStudentId(address.getStudent().getId());
        return dto;
    }

    public Address getAddress(AddressWithStudentIdDTO address){
        var dto=new Address();
        dto.setId(address.getId());
        dto.setCity(address.getCity());
        dto.setStreet(address.getStreet());
        dto.setPostalCode(address.getPostalCode());
        dto.setStudent(db.getStudents().
                findById(address.getStudentId()).stream().toList().get(0));
        return dto;
    }
}
