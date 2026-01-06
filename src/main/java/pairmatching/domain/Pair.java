package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pair {
    private final List<String> pair;

    public Pair(List<String> crewNames) {
        this.pair = crewNames;
    }

    /**
     * TODO!!
     * 동일한 페어인지는 크루 닉네임 사전순 정렬로 String을 만들어 equals 오버라이드
     */
    public boolean isEqualsPair(Pair other) {
        List<String> mine = getNames();
        List<String> another = other.getNames();

        Collections.sort(mine);
        Collections.sort(another);

        return mine.equals(another);
    }

    public List<String> getNames() {
        return new ArrayList<>(pair);
    }

    public String getPairAsString() {
        StringBuilder result = new StringBuilder();
        int pairCount = pair.size();
        for (int i = 0; i < pairCount; i++) {
            if (i != 0) {
                result.append(" | ");
            }
            result.append(pair.get(i));
        }
        return result.toString();
    }
}
