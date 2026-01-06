package pairmatching.domain;

import java.util.List;
import java.util.Objects;

public class Pair {
    private final List<Crew> crews;

    public Pair(List<Crew> crews) {
        this.crews = crews;
    }

    /**
     * 동일한 페어인지는 크루 닉네임 사전순 정렬로 String을 만들어 equals 오버라이드
     *
     * @param o
     * @return
     */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair) o;
        return Objects.equals(crews, pair.crews);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(crews);
    }
}
