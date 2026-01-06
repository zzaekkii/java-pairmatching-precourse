package pairmatching.view;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.MissionInfo;
import pairmatching.domain.Pair;

public class OutputView {
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printFunctionList() {
        System.out.println("기능을 선택하세요.\n"
                + "1. 페어 매칭\n"
                + "2. 페어 조회\n"
                + "3. 페어 초기화\n"
                + "Q. 종료");
    }

    public void printMissionList() {
        System.out.println("\n#############################################");
        printCourses();
        printMissions();
        System.out.println("#############################################");
        System.out.println("과정, 레벨, 미션을 선택하세요.\n"
                + "ex) 백엔드, 레벨1, 자동차경주");
    }

    private static void printCourses() {
        System.out.print("과정: ");
        List<String> courses = new ArrayList<>();
        for (Course course : Course.values()) {
            courses.add(course.getName());
        }
        int courseCount = courses.size();
        for (int i = 0; i < courseCount; i++) {
            if (i != 0) {
                System.out.print(" | ");
            }
            System.out.print(courses.get(i));
        }
        System.out.println();
    }

    private static void printMissions() {
        System.out.println("미션: ");
        for (Level level : Level.values()) {
            System.out.print("\t- " + level.getName() + ": ");

            List<String> missions = new ArrayList<>();
            for (MissionInfo info : MissionInfo.values()) {
                if (info.getLevel().equals(level)) {
                    missions.add(info.getName());
                }
            }
            int missionsCount = missions.size();
            for (int i = 0; i < missionsCount; i++) {
                if (i != 0) {
                    System.out.print(" | ");
                }
                System.out.print(missions.get(i));
            }
            System.out.println();
        }
    }

    public void printReMatchingRequest() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
                + "네 | 아니오");

    }

    public void printMatchingResult(List<Pair> pairs) {
        System.out.println("\n페어 매칭 결과입니다.");
        for (Pair pair : pairs) {
            List<String> names = pair.getNames();
            int namesCount = names.size();
            for (int i = 0; i < namesCount; i++) {
                if (i != 0) {
                    System.out.print(" : ");
                }
                System.out.print(names.get(i));
            }
            System.out.println();
        }
    }
}
