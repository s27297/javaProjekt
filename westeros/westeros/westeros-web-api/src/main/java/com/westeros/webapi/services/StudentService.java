package com.westeros.webapi.services;


import com.westeros.data.clases.Address;
import com.westeros.data.clases.Grade;
import com.westeros.data.clases.Student;
import com.westeros.data.dto.*;
import com.westeros.data.repositories.ICatalogData;

import com.westeros.webapi.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final ICatalogData db;


    private final Mapper mapper;


////////////////////////Get/////////////////////////////////
    @Override
    public List<StudentSummaryDTO> getStudents() {
        return db.getStudents().findAll().stream().
                map(s->mapper.getSummaryStudentDTO(s)).toList();
    }
    @Override
    public GradeWithoutStudentIDDTO getGrade(Integer id) {
        return mapper.getGradeDTO(this.db.getGrades().findById(id)
                .stream().toList().get(0));
    }

    @Override
    public AddressWithStudentIdDTO getAddress(Integer id) {
        return mapper.getAddressDTO(this.db.getAddresses().findById(id)
                .stream().toList().get(0));
    }



    @Override
    public StudentSummaryDTO getStudent(Integer id) {
        Optional<Student> student=db.getStudents().findById(id);
        if(student.isPresent())
            return mapper.getSummaryStudentDTO(db.getStudents().findById(id).stream().toList().get(0));
        else
            return null;
    }
    @Override
    public List<AddressWithStudentIdDTO> getAddresses(Integer id) {
        return db.getAddresses().findAll().stream().filter(address -> address.
                getStudent().getId().equals(id)).toList().stream().
                map(address -> mapper.getAddressDTO(address)).toList();
    }
    @Override
    public List<GradeWithStudentIdDTO> getGrades(Integer id) {
        return db.getGrades().findAll().stream().filter(address -> address.
                getStudent().getId().equals(id)).toList().stream().
                map(address -> mapper.getGradeWithIdDTO(address)).toList();

    }


    //////////////////////ADD///////////////////////////


    @Override
    public Integer addAddress(AddressWithStudentIdDTO addressDTO, Integer id) {
        Optional<Address> address=db.getAddresses().findById(addressDTO.getId());
        Optional<Student> student=db.getStudents().findById(id);
        if(!address.isPresent() && student.isPresent()) {
            db.getAddresses().save(mapper.getAddress(addressDTO));
//            Student student1=student.stream().toList().get(0);
//            List<Address> adresses=student1.getAddresses();
//            adresses.add(mapper.getAddress(addressDTO));
//            student1.setAddresses(adresses);
//            db.getStudents().save(student1);
            return addressDTO.getId();
        }else
            return -1;
    }

    @Override
    public Integer addGrade(GradeWithStudentIdDTO gradeDTO) {
        Optional<Grade> address=db.getGrades().findById(gradeDTO.getId());
        Optional<Student> student=db.getStudents().findById(gradeDTO.getStudentId());
        if(!address.isPresent() && student.isPresent()) {
            db.getGrades().save(mapper.getGrade(gradeDTO));
//            Student student1=student.stream().toList().get(0);
//            List<Grade> adresses=student1.getGrades();
//            adresses.add(mapper.getGrade(gradeDTO));
//            student1.setGrades(adresses);
//            db.getStudents().save(student1);
            return gradeDTO.getId();
        }else
            return -1;
    }
    @Override
    public Integer addStudent(StudentSummaryDTO studentsDTO) {
        Optional<Student> student=db.getStudents().findById(studentsDTO.getId());
        if(!student.isPresent()){


             db.getStudents().save
                     (mapper.getStudent(studentsDTO));
        return 1;}
        else
            return -1;
    }
////////////////Update//////////////////////////////
    @Override
    public Integer updateStudent(StudentSummaryDTO studentsDTO) {
        Optional<Student> student=db.getStudents().findById(studentsDTO.getId());
        if(student.isPresent()) {
            List<Grade> gradese = new ArrayList<>();
            for (Grade grade:this.db.getGrades().findAll().stream().filter(a->a.getStudent().getId().equals(studentsDTO.getId())).toList())
                  {
                gradese.add(grade);
            }
            List<Address> ad = new ArrayList<>();
            for (Address grad:this.db.getAddresses().findAll().stream().filter(a->a.getStudent().getId().equals(studentsDTO.getId())).toList())
            {
                ad.add(grad);
            }
            Student student1=mapper.getStudent(studentsDTO);
            student1.setGrades(gradese);
            student1.setAddresses(ad);
            return db.getStudents().save(student1).getId();
        }
        else
            return -1;
    }

    @Override
    public void updateGrade(GradeWithoutStudentIDDTO dto) {
        var grade = db.getGrades().findById(dto.getId());
        if (grade.isPresent()) {
            Grade grade1 = grade.get();
            grade1.setGrade(dto.getGrade());
            grade1.setSubject(dto.getSubject());
            this.db.getGrades().save(grade1);
        }
    }

        @Override
        public void updateAddress(AddressWithoutStudentIdDTO dto) {
            var grade=db.getAddresses().findById(dto.getId());
            if (grade.isPresent()){
                Address grade1 =grade.get();
                grade1.setCity(dto.getCity());
                grade1.setStreet(dto.getStreet());
                grade1.setPostalCode(dto.getPostalCode());
                this.db.getAddresses().save(grade1);
            }

    }

//////////////////////////Delete//////////////////////////
    @Override
    public Integer delete(Integer id) {
        Optional<Student> student=db.getStudents().findById(id);
        if(student.isPresent()) {
            for (Grade grade: db.getGrades().findAll()
                    .stream().filter(grade -> Objects.equals(grade.getStudent().getId(), id)).toList()
                 ) {
                this.db.getGrades().delete(grade);
            }
            for (Address grade: db.getAddresses().findAll()
                    .stream().filter(grade -> Objects.equals(grade.getStudent().getId(), id)).toList()
            ) {
                this.db.getAddresses().delete(grade);
            }
            db.getStudents().deleteById(id);
                return id;
        }else
            return -1;
    }

    @Override
    public Integer deleteAddress(Integer id) {
        Optional<Address> address=db.getAddresses().findById(id);
        if(address.isPresent()) {
            db.getAddresses().deleteById(id);
            return id;
        }else
            return -1;
    }

    @Override
    public Integer deleteGrade(Integer id) {
        Optional<Grade> address=db.getGrades().findById(id);
        if(address.isPresent()) {
            db.getGrades().deleteById(id);
            return id;
        }else
            return -1;
    }




}
