package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pairmatching.exception.ErrorMessage;

public class Missions {
    private final Map<Course, Map<Level, List<Mission>>> missions;

    public Missions(Map<Course, Map<Level, List<Mission>>> missions) {
        this.missions = missions;
    }

    public List<Mission> getMissions(Course course, Level level) {
        if (!missions.containsKey(course)) {
            throw new IllegalArgumentException(ErrorMessage.MISSION_NOT_FOUND.getMessage());
        }

        Map<Level, List<Mission>> levels = missions.get(course);
        if (!levels.containsKey(level)) {
            throw new IllegalArgumentException(ErrorMessage.MISSION_NOT_FOUND.getMessage());
        }

        return new ArrayList<>(levels.get(level));
    }
}
