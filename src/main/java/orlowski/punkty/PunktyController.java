package orlowski.punkty;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/punkty")
public class PunktyController {
    private StudentService service;

    public PunktyController(StudentService service) {
        this.service = service;
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getUsers() {
        return this.service.getStudents().asJava();
    }
    @RequestMapping(value = "/students", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student addUser(@RequestBody NewStudent student) {
        return this.service.addStudent(student);
    }

    @RequestMapping(value = "/students/{id}/number/{number}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @Transactional Student setNumber(@PathVariable("id") long id, @PathVariable("number") String number) {
        return this.service.changeNumber(id, number).orElseThrow(
                () -> new NoStudentException(id)
        );
    }

    @RequestMapping(value = "/students/{id}/scores", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @Transactional int addScore(@PathVariable("id") long id, @RequestBody Score score) {
        return this.service.addScore(id, score).orElseThrow(
                () -> new NoStudentException(id));
    }
}
