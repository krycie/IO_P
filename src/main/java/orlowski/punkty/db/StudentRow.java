package orlowski.punkty.db;

import orlowski.punkty.Student;

import javax.persistence.*;;import java.util.Set;

@Entity
public class StudentRow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String number;
    private String deansGroup;

    protected StudentRow() {};

    @OneToMany(mappedBy = "student")
    private Set<ScoreRow> scores;

    public StudentRow(String name, String number, String deansGroup) {
        this.name = name;
        this.number = number;
        this.deansGroup = deansGroup;
    }

    public Student toStudent() {
        return new Student(
                this.id,
                this.name,
                this.number,
                this.deansGroup
        );
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public String getDeansGroup() {
        return this.deansGroup;
    }

    public Set<ScoreRow> getScores() {
        return scores;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDeansGroup(String deansGroup) {
        this.deansGroup = deansGroup;
    }

    public void setScores(Set<ScoreRow> scores) {
        this.scores = scores;
    }
}
