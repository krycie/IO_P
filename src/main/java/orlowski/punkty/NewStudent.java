package orlowski.punkty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewStudent {
    @JsonProperty("name") public final String name;
    @JsonProperty("number") public final String number;
    @JsonProperty("deansGroup") public final String deansGroup;

    public NewStudent(String name, String number, String deansGroup) {
        this.name = name;
        this.number = number;
        this.deansGroup = deansGroup;
    }
}
