
package com.vedant.smartcomplaintmanagement.controller;

import com.vedant.smartcomplaintmanagement.dto.StudentResponse;
import com.vedant.smartcomplaintmanagement.model.Student;
import com.vedant.smartcomplaintmanagement.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://smartcomplaintmanagement-frontend.onrender.com"
})
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public StudentResponse registerStudent(@RequestBody Student student) {
        Student savedStudent = studentService.registerStudent(student);

        return new StudentResponse(
            savedStudent.getId(),
            savedStudent.getName(),
            savedStudent.getEmail()
        );
    }

    @PostMapping("/login")
    public StudentResponse loginStudent(@RequestBody Student student) {
        Student loggedInStudent = studentService.loginStudent(
            student.getEmail(),
            student.getPassword()
        );

        return new StudentResponse(
            loggedInStudent.getId(),
            loggedInStudent.getName(),
            loggedInStudent.getEmail()
        );
    }
}
