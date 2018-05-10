package nl.smit.scheduler.referee.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Responsible for keeping track of the path to view files.
 *
 * @author Jordi Smit, 8-2-2018.
 */
@AllArgsConstructor
public enum ViewFxml implements IViewFxml {

    MAIN("MAIN", "/fxml/Main.fxml"),
    NOT_MAIN("NOT MAIN", "/fxml/NotMain.fxml");

    @Getter
    private final String title;
    @Getter
    private final String filePath;
}
