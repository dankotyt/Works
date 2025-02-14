package com.example.testspringboot.repository;

import com.example.testspringboot.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryStudentDAO {
    private final List<Student> STUDENTS = new ArrayList<Student>();

    public List<Student> findAllStudents() {
        return STUDENTS;
    }

    public Student saveStudent(Student student) {
        STUDENTS.add(student);
        return student;
    }

    public Student findStudentByEmail(String email) {
        return STUDENTS.stream()
                .filter(student -> student.getEmail().equals(email))
                .findFirst()
                .orElse(null);

    }

    public Student updateStudent(Student student) {
        var indexStudent = IntStream.range(0, STUDENTS.size())
                .filter(index -> STUDENTS.get(index).getEmail().equals(student.getEmail()))
                .findFirst()
                .orElse(0);
        if (indexStudent > 0) {
            STUDENTS.set(indexStudent, student);
            return student;
        }
        return null;
    }

    public void deleteStudent(String email) {
        var indexStudent = findStudentByEmail(email);
        if (indexStudent != null) {
            STUDENTS.remove(indexStudent);
        }
    }
}
