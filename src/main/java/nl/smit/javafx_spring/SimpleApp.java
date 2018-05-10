package nl.smit.javafx_spring;

import javafx.application.Application;
import nl.smit.javafx_spring.view.ViewFxml;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nl.smit.javafx_spring.view.IViewFxml;

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
    protected String getResourceBundlePath() {
        return "Bundle";
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
