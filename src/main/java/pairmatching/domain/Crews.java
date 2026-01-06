package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pairmatching.exception.ErrorMessage;

public class Crews {
    private final Map<Course, List<Crew>> crews;

    public Crews(Map<Course, List<Crew>> crews) {
        this.crews = crews;
    }

    public List<Crew> getCrews(Course course) {
        if (!crews.containsKey(course)) {
            throw new IllegalArgumentException(ErrorMessage.ETC.getMessage());
        }
        return new ArrayList<>(crews.get(course));
    }
}
