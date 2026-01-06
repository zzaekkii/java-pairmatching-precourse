package pairmatching.domain;

public class MissionToFind {
    private final Course course;
    private final MissionInfo missionInfo;

    public MissionToFind(Course course, MissionInfo missionInfo) {
        this.course = course;
        this.missionInfo = missionInfo;
    }

    public Course getCourse() {
        return course;
    }

    public MissionInfo getMissionInfo() {
        return missionInfo;
    }
}
