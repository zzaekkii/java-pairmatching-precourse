package pairmatching.domain;

import pairmatching.exception.ErrorMessage;

public enum Command {
    MATCHING_PAIR("1"),
    CHECK_PAIR("2"),
    CLEAR_PAIR("3"),
    QUIT("Q");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command of(String value) {
        for (Command command : Command.values()) {
            if (command.value.equals(value)) {
                return command;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
    }
}
