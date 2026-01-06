package pairmatching.domain;

import pairmatching.exception.ErrorMessage;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public static Course fromCourseName(String name) {
        for (Course course : Course.values()) {
            if (course.name.equals(name)) {
                return course;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.COURSE_NOT_FOUND.getMessage());
    }

    public String getName() {
        return name;
    }
}
