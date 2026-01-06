package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Pair {
    private final Set<String> pair;

    public Pair(List<String> crewNames) {
        this.pair = Collections.unmodifiableSet(new HashSet<>(crewNames));
    }

    public String getPairAsString() {
        List<String> names = new ArrayList<>(pair);
        StringBuilder result = new StringBuilder();
        int pairCount = names.size();
        for (int i = 0; i < pairCount; i++) {
            if (i != 0) {
                result.append(" : ");
            }
            result.append(names.get(i));
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair pair1 = (Pair) o;
        return Objects.equals(pair, pair1.pair);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pair);
    }
}
