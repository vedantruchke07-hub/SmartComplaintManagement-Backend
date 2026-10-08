package com.vedant.smartcomplaintmanagement.controller;

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
    public Student registerStudent(@RequestBody Student student) {
        return studentService.registerStudent(student);
    }

    @PostMapping("/login")
    public Student loginStudent(@RequestBody Student student) {

        return studentService.loginStudent(
                student.getEmail(),
                student.getPassword()
        );
    }
}