package pl.emil7f.simplerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.emil7f.simplerestapi.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
