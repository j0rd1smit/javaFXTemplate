package nl.smit.scheduler.referee.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

/**
 * Identifies a controller that will be created by an {@link FXMLLoader}. The
 * {@code FXMLLoader} will automatically inject {@code location} and
 * {@code resources} properties into the controller, and then it will call the
 * no-arg {@link #initialize()} method. This is the recommended approach: don't
 * use the {@link Initializable} interface.
 */
public interface FxmlController {
    /**
     * Initializes the Fxml controller.
     */
    default void initialize() {
        //empty
    }
}
