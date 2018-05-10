package nl.smit.javafx_spring.view;

/**
 * Responsible for keeping track of the path to view files.
 *
 * @author Jordi Smit on 10-5-2018.
 */
public interface IViewFxml {
    /**
     * @return The title to be displayed.
     */
    String getTitle();

    /**
     * @return The path to the FXML file.
     */
    String getFilePath();
}
