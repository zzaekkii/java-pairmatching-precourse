package pairmatching.view;

import static pairmatching.exception.ErrorMessage.EMPTY_INPUT;
import static pairmatching.exception.ErrorMessage.INVALID_FORMAT;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.Command;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.MissionInfo;
import pairmatching.domain.MissionToFind;

public class InputView {
    public Command readFunctionCommand() {
        String input = readAndValidate();

        if (!input.matches("[1-3]|Q")) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }

        return Command.of(input);
    }

    public MissionToFind readCourseAndMissionInfo() {
        String input = readAndValidate();
        validateSeparator(input);

        String[] values = input.split(", ");
        Course course = Course.fromCourseName(values[0]);
        Level level = Level.fromString(values[1]);
        MissionInfo missionInfo = MissionInfo.fromLevelAndMissionName(level, values[2]);

        return new MissionToFind(course, missionInfo);
    }

    public boolean readYesOrNo() {
        String input = readAndValidate();
        if ("네".equals(input)) {
            return true;
        }
        if ("아니오".equals(input)) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
    }

    private static void validateSeparator(String value) {
        if (!value.startsWith(",") || !value.endsWith(",")) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }

        if (value.contains(",,")) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }

        if (value.contains("  ")) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    private static String readAndValidate() {
        String input = readLine();

        nullCheck(input);

        input = input.trim();
        return input;
    }

    private static String readLine() {
        return Console.readLine();
    }

    private static void nullCheck(String input) {
        if (input == null || input.isEmpty() || input.matches("^ +$")) {
            throw new IllegalArgumentException(EMPTY_INPUT.getMessage());
        }
    }
}
