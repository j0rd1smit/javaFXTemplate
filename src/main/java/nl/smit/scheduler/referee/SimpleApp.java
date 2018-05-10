package nl.smit.scheduler.referee;

import javafx.application.Application;
import nl.smit.scheduler.referee.view.IViewFxml;
import nl.smit.scheduler.referee.view.ViewFxml;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * [Explation]
 *
 * @author Jordi Smit on 10-5-2018.
 */
@SpringBootApplication
public class SimpleApp extends SpringJavaFxApplication {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    protected Class getApplicationClass() {
        return SimpleApp.class;
    }

    @Override
    protected IViewFxml getInitialScene() {
        return ViewFxml.MAIN;
    }
}
