package orlowski.punkty;

import orlowski.punkty.db.StudentRepository;
import io.vavr.collection.List;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentRepository repository;

    @After
    public void cleanup() {
        this.repository.deleteAll();
    }

    @Test
    public void getEmptyList() {
        final StudentService service = new StudentService(repository);
        List<Student> students = service.getStudents();
        assertTrue(students.isEmpty());
    }

    @Test
    public void addStudent() {
        StudentService service = new StudentService(repository);
        Student student = service.addStudent(new NewStudent("aa", "aa", "aa"));
        assertNotNull(student);
    }

    @Test
    public void addStudentIsReturned() {
        StudentService service = new StudentService(repository);
        Student student = service.addStudent(new NewStudent("Student1", "1-2-3", "IP"));
        List<Student> students = service.getStudents();
        assertEquals("Student1", students.get(0).name);
    }

    @Test
    public void addStudentHasNewId() {
        StudentService service = new StudentService(repository);
        Student s1 = service.addStudent(new NewStudent("Student1", "1-2-3", "IP"));
        Student s2 = service.addStudent(new NewStudent("Student2", "2-3-4", "IP"));
        List<Student> students = service.getStudents();
        assertNotEquals(students.get(0), students.get(1));
        assertEquals(2, students.size());
    }
}