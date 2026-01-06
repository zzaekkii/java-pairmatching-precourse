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

    public List<String> getCrewsNames(Course course) {
        if (!crews.containsKey(course)) {
            throw new IllegalArgumentException(ErrorMessage.ETC.getMessage());
        }
        return new ArrayList<>(getNames(crews.get(course)));
    }

    private static List<String> getNames(List<Crew> crewList) {
        List<String> names = new ArrayList<>();
        for (Crew crew : crewList) {
            names.add(crew.getName());
        }
        return names;
    }
}
