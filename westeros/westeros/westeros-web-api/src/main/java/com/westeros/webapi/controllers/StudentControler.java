package com.westeros.webapi.controllers;


import com.westeros.data.dto.*;
import com.westeros.webapi.services.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
//@RequestMapping("all")
@RequiredArgsConstructor

public class StudentControler {

    private final IStudentService studentService;
//////////////////////////////////GET///////////////////////////
    @GetMapping("/all")
    public String getStudents(Model model) {
       model.addAttribute("students", studentService.getStudents());
        System.out.println("cat");
        return "index1";
    }

    @GetMapping("Student/{id}")
    public String getStudent(Model model,@PathVariable("id") int id) {
        model.addAttribute("student", studentService.getStudent(id));
        return "Student";
    }

    @GetMapping("{id}/adresses")
    public String getAddresses(Model model,@PathVariable("id") Integer id){
        model.addAttribute("addresses", studentService.getAddresses(id));
           return "addresses";
    }

    @GetMapping("{id}/grades")
    public String getGrades(Model model,@PathVariable("id") Integer id){
        model.addAttribute("grades", studentService.getGrades(id));
        return "grades";
    }








///////////////////////////////DELETE//////////////////////////////////////////////////////////////////
   @GetMapping(value = "deleteGrade/{id}")
   public String deleteGrade( Model model,@PathVariable("id") Integer id) {
       model.addAttribute("grad", this.studentService.getGrade(id));

       return "deleteGrade";
   }

     @RequestMapping(value = "/deleteGrade")
    public String deleteGrade( @ModelAttribute GradeWithoutStudentIDDTO grade) {
        this.studentService.deleteGrade(grade.getId());
        return "redirect:/all";
    }



    @GetMapping(value = "deleteAddress/{id}")
    public String deleteAddress( Model model,@PathVariable("id") Integer id) {
        model.addAttribute("address", this.studentService.getAddress(id));

        return "deleteAddress";
    }

    @RequestMapping(value = "/deleteAddress")
    public String deleteAddress( @ModelAttribute AddressWithoutStudentIdDTO grade) {
        this.studentService.deleteAddress(grade.getId());
        return "redirect:/all";
    }




    @GetMapping(value = "deleteStudent/{id}")
    public String deleteStudent( Model model,@PathVariable("id") Integer id) {
        model.addAttribute("student", this.studentService.getStudent(id));
        return "deleteStudent";
    }

    @RequestMapping(value = "/deleteStudent")
    public String deleteStudent( @ModelAttribute StudentDTO studentDTO) {
        this.studentService.delete(studentDTO.getId());
        return "redirect:/all";
    }

/////////////////////////////////////ADD//////////////////////////////////////////////

    @GetMapping(value = "newStudent")
    public String nowyStudent(Model model) {
        model.addAttribute("student", new StudentSummaryDTO(-1,"qwer", "q"));
        return "newStudent";
    }
    @RequestMapping(value = "saveStudent", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute StudentSummaryDTO student, BindingResult errors, Model model) {
        Integer id=this.studentService.addStudent(student);
        return "redirect:/all";
}

    @GetMapping(value = "newAddress/{id}")
    public String newAddress(Model model,@PathVariable("id")Integer id) {
        model.addAttribute("address",
                new AddressWithStudentIdDTO(-1,"qwer","we","q",id));
        return "newAdress";
    }
    @RequestMapping(value = "saveAddress", method = RequestMethod.POST)
    public String saveAddress(@ModelAttribute AddressWithStudentIdDTO student, BindingResult errors, Model model) {
        Integer id=this.studentService.addAddress(student,student.getStudentId());
        return "redirect:/all";
    }


    @GetMapping(value = "newGrade/{idd}")
    public String newGrade(Model model,@PathVariable("idd")Integer idd) {
        model.addAttribute("grad",
                new GradeWithStudentIdDTO(-1,"q","4+",idd));
        return "newGrade";
    }
    @RequestMapping(value = "saveGrade", method = RequestMethod.POST)
    public String saveGrade(@ModelAttribute GradeWithStudentIdDTO student, BindingResult errors, Model model) {
        Integer id=this.studentService.addGrade(student);
        return "redirect:/all";
    }

    ///////////////////////UPDATE////////////////



    @GetMapping(value = "updateStudent/{id}")
    public String updateStudent(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("student", new StudentDTO(id, "", "",studentService.
                getAddresses(id),
                studentService.getGrades(id)));
        return "updateStudent";
    }

    @RequestMapping(value = "updateStudent", method = RequestMethod.POST)
    public String updateStudent(@ModelAttribute StudentSummaryDTO student, BindingResult errors, Model model) {
        this.studentService.updateStudent(student);
        return "redirect:/all";
    }

    @GetMapping(value = "updateGrade/{id}")
    public String updateGrade( Model model,@PathVariable("id") Integer id) {
        GradeWithoutStudentIDDTO grade= new GradeWithoutStudentIDDTO();
        grade.setId(id);
        model.addAttribute("grade", grade);
        return "UpdateGrade";
    }
    @RequestMapping(value = "updateGrade", method = RequestMethod.POST)
    public String updateGrade(@ModelAttribute GradeWithoutStudentIDDTO student, BindingResult errors, Model model) {
        this.studentService.updateGrade(student);
        return "redirect:/all";
    }


    @GetMapping(value = "updateAddress/{id}")
    public String updateAddress( Model model,@PathVariable("id") Integer id) {
        AddressWithoutStudentIdDTO grade= new AddressWithoutStudentIdDTO();
        grade.setId(id);
        model.addAttribute("address", grade);
        return "UpdateAddress";
    }
    @RequestMapping(value = "updateAddress", method = RequestMethod.POST)
    public String updateAddress(@ModelAttribute AddressWithoutStudentIdDTO student, BindingResult errors, Model model) {
        this.studentService.updateAddress(student);
        return "redirect:/all";
    }







}




