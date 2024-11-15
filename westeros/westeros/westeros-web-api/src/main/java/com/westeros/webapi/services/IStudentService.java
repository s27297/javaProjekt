package com.westeros.webapi.services;



import com.westeros.data.dto.*;

import java.util.List;

public interface IStudentService {


    List<StudentSummaryDTO> getStudents();

    StudentSummaryDTO getStudent(Integer id);

    List<AddressWithStudentIdDTO> getAddresses(Integer id);
    List<GradeWithStudentIdDTO> getGrades(Integer id);

    Integer addStudent(StudentSummaryDTO studentsDTO);

    Integer updateStudent(StudentSummaryDTO kapibara);

    Integer delete(Integer id);

    Integer deleteAddress(Integer id);



    Integer deleteGrade(Integer id);


    Integer addAddress(AddressWithStudentIdDTO addressDTO, Integer id);



    Integer addGrade(GradeWithStudentIdDTO gradeDTO);

    GradeWithoutStudentIDDTO getGrade(Integer id);

    AddressWithStudentIdDTO getAddress(Integer id);

    void updateGrade(GradeWithoutStudentIDDTO student);

    void updateAddress(AddressWithoutStudentIdDTO student);
}
