package nl.smit.scheduler.referee;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import nl.smit.scheduler.referee.view.IViewFxml;
import nl.smit.scheduler.referee.view.StageManager;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Responsible for starting the spring javafx application.
 *
 * @author Jordi Smit on 10-5-2018.
 */
public abstract class SpringJavaFxApplication extends Application {

    @MonotonicNonNull
    @Getter(AccessLevel.PROTECTED)
    private ConfigurableApplicationContext springContext;
    @MonotonicNonNull
    private StageManager stageManager;


    @Override
    @EnsuresNonNull("springContext")
    public void init() {
        springContext = bootstrapSpringApplicationContext();
    }

    private ConfigurableApplicationContext bootstrapSpringApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(getApplicationClass());
        String[] args = getParameters().getRaw().toArray(new String[0]);
        builder
                .headless(false)
                .web(false);

        return builder.run(args);
    }

    protected abstract Class getApplicationClass();

    @Override
    @EnsuresNonNull("stageManager")
    @RequiresNonNull("springContext")
    public void start(Stage stage) {
        stageManager = springContext.getBean(StageManager.class, stage);
        prepareInitialScene();
        displayInitialScene();
    }



    @RequiresNonNull("stageManager")
    private void displayInitialScene() {
        stageManager.switchScene(getInitialScene());

    }

    /**
     * Maximizes the scene to the screen size.
     */
    protected void maximizeScene() {
        if (stageManager == null) {
            throw new RuntimeException("The application has not yet been started.");
        }
        stageManager.maximizeStage();
    }

    /**
     * Prepares the initial Scene before displaying it.
     */
    protected void prepareInitialScene() { }

    /**
     * Sets the initial scene of the application.
     * Can be overwrite for testing purposes.
     */
    protected abstract IViewFxml getInitialScene();


    @Override
    @RequiresNonNull("springContext")
    public void stop() throws Exception {
        super.stop();
        springContext.close();
    }
}
