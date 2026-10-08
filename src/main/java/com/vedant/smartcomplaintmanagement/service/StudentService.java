package com.vedant.smartcomplaintmanagement.service;

import com.vedant.smartcomplaintmanagement.model.Student;
import com.vedant.smartcomplaintmanagement.repository.StudentRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student registerStudent(Student student) {

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new RuntimeException(
                    "Student with this email already exists"
            );
        }

        String hashedPassword =
                passwordEncoder.encode(student.getPassword());

        student.setPassword(hashedPassword);

        return studentRepository.save(student);
    }

    public Student loginStudent(String email, String password) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Invalid email or password"
                        )
                );

        if (!passwordEncoder.matches(
                password,
                student.getPassword()
        )) {
            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        return student;
    }
}