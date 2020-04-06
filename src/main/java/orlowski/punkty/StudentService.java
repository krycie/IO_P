package orlowski.punkty;

import orlowski.punkty.db.ScoreRepository;
import orlowski.punkty.db.ScoreRow;
import orlowski.punkty.db.StudentRepository;
import orlowski.punkty.db.StudentRow;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;

    public StudentService(StudentRepository repository, ScoreRepository scoreRepository) {
        this.studentRepository = repository;
        this.scoreRepository = scoreRepository;
    }

    List<Student> getStudents() {
        return List.ofAll(this.studentRepository.findAll()).map(StudentRow::toStudent);
    }

    Student addStudent(NewStudent student) {
        return this.studentRepository.save(new StudentRow(
                student.name,
                student.number,
                student.deansGroup
        )).toStudent();
    }

    Optional<Student> changeNumber(long studentId, String newNumber) {
        final Optional<StudentRow> student = this.studentRepository.findById(studentId);

        return student.map(c -> {
            c.setNumber(newNumber);
            return c.toStudent();
        });
    }

    public Optional<Integer> addScore(final long id, final Score score) {
        final Optional<StudentRow> student = this.studentRepository.findById(id);
        return student.map(c -> {
            int currentScore = List.ofAll(c.getScores()).foldLeft(0, (p, s) -> p + s.getScore());
            final ScoreRow newScore = new ScoreRow(score.score, score.comment, c);
            this.scoreRepository.save(newScore);
            return currentScore + score.score;
        });
    }
}
