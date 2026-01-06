package pairmatching.controller;

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
import pairmatching.domain.Missions;
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
