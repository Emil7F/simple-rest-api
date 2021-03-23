package pl.emil7f.simplerestapi.service;

import pl.emil7f.simplerestapi.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents(Student.Status status);

    Student getStudent(Long id);

    Student addStudent(Student student);

    void deleteStudent(Long id);

    Student putStudent(Long id, Student student);

    Student patchStudent(Long id, Student student);
}
