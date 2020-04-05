package orlowski.punkty.db;

import javax.persistence.Id;;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class StudentRow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String number;
    private String deansGroup;

    protected StudentRow() {};
    public StudentRow(String name, String number, String deansGroup) {
        System.out.println("tak");
        this.name = name;
        this.number = number;
        this.deansGroup = deansGroup;
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
}
