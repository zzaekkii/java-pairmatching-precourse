package pairmatching.exception;

public enum ErrorMessage {
    EMPTY_INPUT("값을 입력해야 합니다."),
    COURSE_NOT_FOUND("과정이 존재하지 않습니다."),
    LEVEL_NOT_FOUND("레벨이 존재하지 않습니다."),
    MISSION_NOT_FOUND("미션이 존재하지 않습니다."),
    MATCHING_NOT_FOUND("매칭 이력이 없습니다."),
    MATCHING_IMPOSSIBLE("페어 매칭에 실패했습니다."),
    INVALID_FORMAT("잘못된 형식을 입력하였습니다."),
    ETC("오류가 발생했습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}