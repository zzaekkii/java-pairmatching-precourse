package pairmatching.view;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Crew;

public class FileInputView {
    public List<Crew> readBackendCrews() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/backend-crew.md"));
        List<Crew> crews = new ArrayList<>();

        for (String line : lines) {
            crews.add(new Crew(line));
        }
        return crews;
    }

    public List<Crew> readFrontendCrews() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/frontend-crew.md"));
        List<Crew> crews = new ArrayList<>();

        for (String line : lines) {
            crews.add(new Crew(line));
        }
        return crews;
    }
}
