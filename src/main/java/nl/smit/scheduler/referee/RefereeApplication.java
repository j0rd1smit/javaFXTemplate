package nl.smit.scheduler.referee;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Getter;
import nl.smit.scheduler.referee.view.StageManager;
import nl.smit.scheduler.referee.view.ViewFxml;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Starts the Fxml based Javafx application.
 *
 * @author Jordi Smit, 26-12-2017.
 */
@SpringBootApplication
public class RefereeApplication extends Application {
    @MonotonicNonNull
    @Getter
    private ConfigurableApplicationContext springContext;
    @MonotonicNonNull
    private StageManager stageManager;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    @EnsuresNonNull("springContext")
    public void init() {
        springContext = bootstrapSpringApplicationContext();
    }

    private ConfigurableApplicationContext bootstrapSpringApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(RefereeApplication.class);
        String[] args = getParameters().getRaw().toArray(new String[0]);
        builder
                .headless(false)
                .web(false);

        return builder.run(args);
    }

    @Override
    @EnsuresNonNull("stageManager")
    @RequiresNonNull("springContext")
    public void start(Stage stage) {
        stageManager = springContext.getBean(StageManager.class, stage);
        prepareInitialScene();
        displayInitialScene();
    }

    protected void prepareInitialScene() {

    }


    @RequiresNonNull("stageManager")
    private void displayInitialScene() {
        stageManager.switchScene(getInitialScene());
        stageManager.maximizeStage();
    }

    /**
     * Sets the initial scene of the application.
     * Can be overwrite for testing purposes.
     */
    protected ViewFxml getInitialScene() {
        return ViewFxml.MAIN;
    }

    @Override
    @RequiresNonNull("springContext")
    public void stop() {
        springContext.close();
    }
}
