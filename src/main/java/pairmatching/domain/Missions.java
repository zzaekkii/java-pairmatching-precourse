package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pairmatching.exception.ErrorMessage;

public class Missions {

    private static final int MAXIMUM_TRY = 3;

    private final Map<Course, Map<Level, List<Mission>>> missions;

    public Missions(Map<Course, Map<Level, List<Mission>>> missions) {
        this.missions = missions;
    }

    public List<Pair> matchingPair(MissionToFind missionToFind, Crews crews) {
        Level level = missionToFind.getMissionInfo().getLevel();
        List<Mission> missionsAtLevel = missions.get(missionToFind.getCourse()).get(level);
        Mission mission = getMission(missionToFind, missionsAtLevel);
        clearPreviousMatching(mission);

        List<String> crewNames = crews.getCrewsNames(missionToFind.getCourse());
        for (int t = 0; t < MAXIMUM_TRY; t++) {
            List<String> shuffledNames = shuffleCrewList(crewNames);
            boolean oddCrews = (shuffledNames.size() % 2) > 0;

            List<Pair> newPairs = new ArrayList<>();
            boolean alreadyExistPair = false;
            int namesCount = shuffledNames.size();
            for (int i = 0; i < namesCount; i += 2) {
                // 홀수 크루들은 마지막 페어 3명
                if (oddCrews && i == (namesCount - 3)) {
                    Pair newPair = makePair(shuffledNames.subList(i, i + 3));
                    if (isExistPair(missionsAtLevel, newPair)) {
                        alreadyExistPair = true;
                        break;
                    }
                    newPairs.add(newPair);
                    continue;
                }

                Pair newPair = makePair(shuffledNames.subList(i, i + 2));
                if (isExistPair(missionsAtLevel, newPair)) {
                    alreadyExistPair = true;
                    break;
                }
                newPairs.add(newPair);
            }

            if (alreadyExistPair) {
                continue;
            }

            mission.setPairs(newPairs);
            return newPairs;
        }
        throw new IllegalArgumentException(ErrorMessage.MATCHING_IMPOSSIBLE.getMessage());
    }

    private Pair makePair(List<String> crewNames) {
        return new Pair(crewNames);
    }

    private boolean isExistPair(List<Mission> missionsAtLevel, Pair newPair) {
        for (Mission mission : missionsAtLevel) {
            if (mission.isExistPair(newPair)) {
                return true;
            }
        }
        return false;
    }

    private void clearPreviousMatching(Mission mission) {
        mission.clearPair();
    }

    public boolean isMatchingExist(MissionToFind missionToFind) {
        Level level = missionToFind.getMissionInfo().getLevel();
        List<Mission> missionsAtLevel = missions.get(missionToFind.getCourse()).get(level);
        for (Mission mission : missionsAtLevel) {
            if (!mission.getInfo().equals(missionToFind.getMissionInfo())) {
                continue;
            }
            if (mission.isMatching()) {
                return true;
            }
        }
        return false;
    }

    private List<String> shuffleCrewList(List<String> crewList) {
        return Randoms.shuffle(crewList);
    }

    private Mission getMission(MissionToFind missionToFind, List<Mission> missionsAtLevel) {
        for (Mission mission : missionsAtLevel) {
            if (!mission.getInfo().equals(missionToFind.getMissionInfo())) {
                continue;
            }
            return mission;
        }
        throw new IllegalArgumentException(ErrorMessage.MISSION_NOT_FOUND.getMessage());
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
