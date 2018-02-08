package nl.smit.scheduler.referee.helper.gui;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import nl.smit.scheduler.referee.RefereeApplication;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ConfigurableApplicationContext;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

/**
 * Sets up the application for TestFx tests.
 *
 * @author Jordi Smit, 8-2-2018.
 */
public class TestFxBase extends ApplicationTest {

    @SuppressWarnings("nullness")
    @Getter(AccessLevel.PROTECTED)
    private static ResourceBundle bundle;

    @Getter(AccessLevel.PROTECTED)
    @SuppressWarnings("nullness")
    private Stage primaryStage;
    @SuppressWarnings("nullness")
    private RefereeApplication application;


    @BeforeAll
    static void beforeAll() {
        bundle = ResourceBundle.getBundle("Bundle");
        boolean headless = Boolean.parseBoolean(bundle.getString("tests.testfx.headless"));
        if (headless) {
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        }
    }

    @BeforeEach
    void beforeEachTestFXBase() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        application = (RefereeApplication) FxToolkit.setupApplication(getApplicationClass());
    }

    protected Class getApplicationClass() {
        return RefereeApplication.class;
    }

    protected ConfigurableApplicationContext getSpringContext() {
        ConfigurableApplicationContext springContext = application.getSpringContext();
        if (springContext == null) {
            throw new RuntimeException("Should not be null at this point.");
        }
        return springContext;
    }

    @AfterEach
    void afterEachTestFXBase() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});

        Flyway flyway = getSpringContext().getBean(Flyway.class);
        flyway.migrate();
        flyway.clean();
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.show();
    }

    protected void ensureEventQueueComplete() {
        WaitForAsyncUtils.waitForFxEvents(1);
    }
}