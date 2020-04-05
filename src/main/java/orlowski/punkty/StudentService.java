package orlowski.punkty;

import orlowski.punkty.db.StudentRepository;
import orlowski.punkty.db.StudentRow;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    private Function<StudentRow, Student> mapStudent() {
        return dbObj ->
            new Student(
                    dbObj.getId(),
                    dbObj.getName(),
                    dbObj.getNumber(),
                    dbObj.getDeansGroup()
            );
    }

    List<Student> getStudents() {
        return List.ofAll(this.repository.findAll()).map(dbObj -> new Student(
                dbObj.getId(),
                dbObj.getName(),
                dbObj.getNumber(),
                dbObj.getDeansGroup()
        ));
    }

    Student addStudent(NewStudent student) {
        StudentRow created = this.repository.save(new StudentRow(
                student.name,
                student.number,
                student.deansGroup
        ));
        return mapStudent().apply(created);
    }
}
