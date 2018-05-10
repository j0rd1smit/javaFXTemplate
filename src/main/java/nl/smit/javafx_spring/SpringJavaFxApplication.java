package nl.smit.javafx_spring;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import nl.smit.javafx_spring.view.IViewFxml;
import nl.smit.javafx_spring.view.StageManager;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.ResourceBundle;

/**
 * Responsible for starting the spring javafx application.
 *
 * @author Jordi Smit on 10-5-2018.
 */
@ComponentScan("nl.smit.javafx_spring")
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
        createResourceBundleBean();
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

    @RequiresNonNull("springContext")
    private void createResourceBundleBean() {
        springContext.getBeanFactory().registerSingleton("ResourceBundle", getResourceBundle());
    }

    /**
     * @return The the resources bundle.
     */
    protected abstract ResourceBundle getResourceBundle();

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
