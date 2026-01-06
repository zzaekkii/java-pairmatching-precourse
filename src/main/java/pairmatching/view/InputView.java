package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.Command;
import pairmatching.exception.ErrorMessage;

public class InputView {
    public Command readFunctionCommand() {
        String input = readAndValidate();

        if (!input.matches("[1-3]|Q")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }

        return Command.of(input);
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
            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT.getMessage());
        }
    }
}
