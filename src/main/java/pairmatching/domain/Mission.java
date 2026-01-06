package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Mission {
    private final MissionInfo info;
    private List<Pair> pairs = new ArrayList<>();

    public Mission(MissionInfo info) {
        this.info = info;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public void clearPair() {
        pairs.clear();
    }

    public boolean isExistPair(Pair newPair) {
        for (Pair pair : pairs) {
            if (pair.equals(newPair)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMatching() {
        return !pairs.isEmpty();
    }

    public MissionInfo getInfo() {
        return info;
    }

    public List<Pair> getPairs() {
        return new ArrayList<>(pairs);
    }
}
