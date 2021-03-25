package pl.emil7f.simplerestapi.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.emil7f.simplerestapi.exception.StudentError;
import pl.emil7f.simplerestapi.exception.StudentException;
import pl.emil7f.simplerestapi.model.Student;
import pl.emil7f.simplerestapi.repository.StudentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    void setup() {
        student = new Student();
        student = prepareStudent();
    }


    @Test
    @DisplayName("Should return student with given id")
    void test1() {
        //given
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(student));
        //when
        Student newStudent = studentService.getStudent(1L);
        //then
        assertThat(newStudent).isEqualTo(student);
        assertThat(newStudent).isNotNull();
    }

    @Test
    @DisplayName("Should throw STUDENT_NOT_FOUND")
    void test2() {
        // given
        Mockito.when(studentRepository.findById(3L)).thenThrow(new StudentException(StudentError.STUDENT_NOT_FOUND));
        // when then
        Assertions.assertThrows(StudentException.class, () -> studentService.getStudent(3L));
    }

    @Test
    @DisplayName("Should throw STUDENT_IS_NOT_ACTIVE")
    void test3() {
        // given
        Mockito.when(studentRepository.findById(2L)
                .map(stud -> stud.getStatus()).equals(Student.Status.INACTIVE))
                .thenThrow(new StudentException(StudentError.STUDENT_IS_NOT_ACTIVE));
        // when

        //then
        Assertions.assertThrows(StudentException.class, () -> studentService.getStudent(2L));

    }


    private Student prepareStudent() {
        student.setId(1L);
        student.setFirstName("First");
        student.setLastName("Last");
        student.setEmail("student@gmail.com");
        student.setStatus(Student.Status.ACTIVE);

        return student;
    }

}
