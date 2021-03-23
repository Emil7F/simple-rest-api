package pl.emil7f.simplerestapi.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.emil7f.simplerestapi.exception.StudentError;
import pl.emil7f.simplerestapi.exception.StudentException;
import pl.emil7f.simplerestapi.model.Student;
import pl.emil7f.simplerestapi.repository.StudentRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents(Student.Status status) {
        if (status != null) {
            return studentRepository.findAllByStatus(status);
        }
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
        if (!Student.Status.ACTIVE.equals(student.getStatus())) {
            throw new StudentException((StudentError.STUDENT_IS_NOT_ACTIVE));
        }
        return student;
    }

    @Override
    public Student addStudent(@Valid Student student) {
        validateStudentEmailExists(student);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
        student.setStatus(Student.Status.INACTIVE);
        studentRepository.save(student);
    }

    @Override
    public Student putStudent(Long id, Student student) {
        return studentRepository.findById(id)
                .map(studentFromDb -> {
                    if (studentRepository.existsByEmail(student.getEmail()) &&
                    !studentFromDb.getEmail().equals(student.getEmail())) {
                        throw new StudentException(StudentError.STUDENT_EMAIL_ALREADY_EXIST);
                    }
                    studentFromDb.setFirstName(student.getFirstName());
                    studentFromDb.setLastName(student.getLastName());
                    studentFromDb.setEmail(student.getEmail());
                    studentFromDb.setStatus(student.getStatus());
                    return studentRepository.save(studentFromDb);
                })
                .orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
    }

    private void validateStudentEmailExists(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new StudentException(StudentError.STUDENT_EMAIL_ALREADY_EXIST);
        }
    }

    @Override
    public Student patchStudent(Long id, Student student) {
        return studentRepository.findById(id)
                .map(studentFromDb -> {
                    if (!StringUtils.isEmpty(student.getFirstName())) {
                        studentFromDb.setFirstName(student.getFirstName());
                    }
                    if (!StringUtils.isEmpty(student.getLastName())) {
                        studentFromDb.setLastName(student.getLastName());
                    }
                    if (!StringUtils.isEmpty(student.getStatus())) {
                        studentFromDb.setStatus(student.getStatus());
                    }
                    return studentRepository.save(studentFromDb);
                })
                .orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
    }
}
