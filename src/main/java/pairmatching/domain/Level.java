package pairmatching.domain;

import pairmatching.exception.ErrorMessage;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private final String name;

    Level(String name) {
        this.name = name;
    }

    public static Level fromString(String value) {
        for (Level level : Level.values()) {
            if (level.name.equals(value)) {
                return level;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.LEVEL_NOT_FOUND.getMessage());

    }

    public String getName() {
        return name;
    }
}
