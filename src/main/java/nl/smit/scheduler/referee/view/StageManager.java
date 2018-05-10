package nl.smit.scheduler.referee.view;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import nl.smit.scheduler.referee.spring.config.SpringFxmlLoader;
import org.slf4j.Logger;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Responsible for changing the scene on the primary stage.
 *
 * @author Jordi Smit, 8-2-2018.
 */
@RequiredArgsConstructor
public class StageManager {
    private static final Logger LOG = getLogger(StageManager.class);

    private final Stage primaryStage;
    private final SpringFxmlLoader springFxmlLoader;

    /**
     * Changes the scene of the primary stage to a new view.
     * @param view The requested view.
     */
    public void switchScene(IViewFxml view) {
        Parent rootNode = loadView(view.getFilePath());
        showView(rootNode, view.getTitle());
    }

    private Parent loadView(String filePath) {
        try {
            return springFxmlLoader.load(filePath);
        } catch (IOException e) {
            String errorMessage = "Unable to load FXML view: " + filePath;
            logAndExit(errorMessage, e);
            throw new RuntimeException(errorMessage);
        }
    }

    private void logAndExit(String errorMsg, Exception exception) {
        LOG.error(errorMsg, exception, exception.getCause());
        Platform.exit();
    }

    private void showView(Parent rootNode, String title) {
        Scene scene = prepareScene(rootNode);

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();

        try {
            primaryStage.show();
        } catch (Exception e) {
            logAndExit("Unable to show scene for title" + title, e);
        }
    }

    private Scene prepareScene(Parent rootNode) {
        Scene scene = primaryStage.getScene();
        if (scene == null) {
            scene = new Scene(rootNode);
        }

        scene.setRoot(rootNode);

        return scene;
    }

    /**
     * Replaces the content of a container with a the FXML of a {@link SubViewFxml}.
     * @param subView The requested subview.
     * @param container The contrainer that will hold the new fxml.
     */
    public void loadSubScene(ISubViewFxml subView, Pane container) {
        Node rootNode = loadView(subView.getFilePath());
        container.getChildren().setAll(rootNode);
    }

    /**
     * Maximizes the stage the window size.
     */
    public void maximizeStage() {
        primaryStage.setMaximized(true);
    }
}
