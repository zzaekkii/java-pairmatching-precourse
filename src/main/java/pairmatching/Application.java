package pairmatching;

import pairmatching.controller.MatchingController;
import pairmatching.view.FileInputView;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        new MatchingController(
                new FileInputView(),
                new InputView(),
                new OutputView()
        ).run();
    }
}
