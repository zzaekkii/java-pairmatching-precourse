package pairmatching.domain;

import static pairmatching.domain.Level.LEVEL1;
import static pairmatching.domain.Level.LEVEL2;
import static pairmatching.domain.Level.LEVEL4;

import java.util.ArrayList;
import java.util.List;
import pairmatching.exception.ErrorMessage;

public enum MissionInfo {
    RACING_CAR("자동차경주", LEVEL1),
    LOTTO("로또", LEVEL1),
    BASEBALL("숫자야구게임", LEVEL1),

    SHOPPING_BAG("장바구니", LEVEL2),
    PAYMENT("결제", LEVEL2),
    SUBWAY_PATHS("지하철노선도", LEVEL2),

    INCREASE_PERFORMANCE("성능개선", LEVEL4),
    RELEASE("배포", LEVEL4);

    private final String name;
    private final Level level;

    MissionInfo(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public static List<MissionInfo> fromLevel(Level level) {
        List<MissionInfo> levels = new ArrayList<>();
        for (MissionInfo mission : MissionInfo.values()) {
            if (mission.level.equals(level)) {
                levels.add(mission);
            }
        }
        return levels;
    }

    public static MissionInfo fromMissionName(String name) {
        for (MissionInfo mission : MissionInfo.values()) {
            if (mission.name.equals(name)) {
                return mission;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.MISSION_NOT_FOUND.getMessage());
    }
}
