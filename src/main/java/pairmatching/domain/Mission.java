package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Mission {
    private final MissionInfo info;
    private List<Pair> pairs = new ArrayList<>();

    public Mission(MissionInfo info) {
        this.info = info;
    }
}
