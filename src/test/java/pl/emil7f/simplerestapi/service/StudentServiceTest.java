package pl.emil7f.simplerestapi.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import pl.emil7f.simplerestapi.model.Student;
import pl.emil7f.simplerestapi.repository.StudentRepository;

import java.util.*;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private final StudentRepository studentRepository = mock(StudentRepository.class);

    @InjectMocks
    private final StudentService studentService = new StudentService(studentRepository);

    private Student student;

    @BeforeEach
    void setup() {
        student = new Student();
        student.setEmail("email@email.com");
        student.setFirstName("FirstName");
        student.setLastName("LastName");
        student.setId(1L);
    }

    @Test
    @DisplayName("When getStudent by id should return student")
    void test1() {
        // given
        when(studentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(student));
        // when
        Optional<Student> optionalStudent = studentService.getStudent(1L);
        //then
        assertThat(this.student, Matchers.is(Matchers.notNullValue()));
        assertThat(this.student.getEmail(), Matchers.equalTo(optionalStudent.get().getEmail()));
        assertThat(this.student.getFirstName(), Matchers.equalTo(optionalStudent.get().getFirstName()));
        assertThat(this.student.getLastName(), Matchers.equalTo(optionalStudent.get().getLastName()));
    }

    @Test
    @DisplayName("When save students should return the same students")
    void test2() {
        // given
        when(studentService.save(ArgumentMatchers.any(Student.class))).thenReturn(student);
        // when
        Student savedStudent = studentService.save(student);
        //then
        assertThat(savedStudent, Matchers.equalTo(student));
        assertThat(savedStudent, Matchers.notNullValue());
    }

    @Test
    @DisplayName("When findAll should return list of students")
    void test3() {
        // given
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        when(studentService.findAll()).thenReturn(studentList);
        // when
        List<Student> students = studentService.findAll();
        //then
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> students.get(1));
        assertThat(students, Matchers.hasSize(1));

    }

}
