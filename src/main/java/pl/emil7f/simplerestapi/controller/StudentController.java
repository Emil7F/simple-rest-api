package pl.emil7f.simplerestapi.controller;

import org.springframework.web.bind.annotation.*;
import pl.emil7f.simplerestapi.repository.StudentRepository;
import pl.emil7f.simplerestapi.model.Student;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public Student addStudent(@RequestBody @Valid Student student) {
        return studentRepository.save(student);
    }

}
