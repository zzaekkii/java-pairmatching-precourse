package pairmatching.controller;

import static pairmatching.domain.Command.CLEAR_PAIR;
import static pairmatching.domain.Command.MATCHING_PAIR;
import static pairmatching.domain.Command.QUIT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.Command;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Crews;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.MissionInfo;
import pairmatching.domain.MissionToFind;
import pairmatching.domain.Missions;
import pairmatching.domain.Pair;
import pairmatching.view.FileInputView;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MatchingController {
    private final FileInputView fileInputView;
    private final InputView inputView;
    private final OutputView outputView;

    public MatchingController(FileInputView fileInputView, InputView inputView, OutputView outputView) {
        this.fileInputView = fileInputView;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Missions missions = initializeMissions();
        Crews crews = initializeCrews();

        while (true) {
            Command command = readFunctionCommand();

            if (command.equals(MATCHING_PAIR)) {
                matchingPair(missions, crews);
            }

            if (command.equals(CLEAR_PAIR)) {
                clearAllPair(missions);
            }

            if (command.equals(QUIT)) {
                break;
            }
        }
    }

    private Command readFunctionCommand() {
        while (true) {
            outputView.printFunctionList();
            try {
                return inputView.readFunctionCommand();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void matchingPair(Missions missions, Crews crews) {
        MissionToFind missionToFind = getMissionToFind();

        if (missions.isMatchingExist(missionToFind)) {
            if (!getYesOrNo()) {
                return;
            }
        }

        List<Pair> pairs = missions.matchingPair(missionToFind, crews);
        outputView.printMatchingResult(pairs);
    }

    private void clearAllPair(Missions missions) {
        missions.clearAllPair();
        outputView.printClearPair();
    }

    private boolean getYesOrNo() {
        while (true) {
            outputView.printReMatchingRequest();
            try {
                return inputView.readYesOrNo();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private MissionToFind getMissionToFind() {
        while (true) {
            outputView.printMissionList();
            try {
                return inputView.readCourseAndMissionInfo();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Crews initializeCrews() {
        Map<Course, List<Crew>> crews = new HashMap<>();
        crews.put(Course.BACKEND, readBackendCrewsFromMd());
        crews.put(Course.FRONTEND, readFrontendCrewsFromMd());
        return new Crews(crews);
    }

    private List<Crew> readBackendCrewsFromMd() {
        try {
            return fileInputView.readBackendCrews();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
        }
        return java.util.Collections.emptyList();
    }

    private List<Crew> readFrontendCrewsFromMd() {
        try {
            return fileInputView.readFrontendCrews();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
        }
        return java.util.Collections.emptyList();
    }

    private Missions initializeMissions() {
        Map<Level, List<Mission>> levels = new HashMap<>();
        for (Level level : Level.values()) {
            List<MissionInfo> infos = MissionInfo.fromLevel(level);
            List<Mission> missions = new ArrayList<>();
            for (MissionInfo missionInfo : infos) {
                missions.add(new Mission(missionInfo));
            }

            levels.put(level, missions);
        }

        Map<Course, Map<Level, List<Mission>>> courseMissions = new HashMap<>();
        for (Course course : Course.values()) {
            courseMissions.put(course, levels);
        }

        return new Missions(courseMissions);
    }
}
