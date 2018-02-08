package nl.smit.scheduler.referee.spring.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Responsible for the loading FXML file from the resources.
 *
 * @author Jordi Smit, 8-2-2018.
 */
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SpringFxmlLoader {
    private final ResourceBundle resourceBundle;
    private final ApplicationContext context;

    /**
     * Loads an FXML file.
     * @param fxmlPath The path the FXML file.
     * @return The FXML file as Parent node.
     * @throws IOException When the FXML file cannot be found.
     */
    public Parent load(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(context::getBean);
        loader.setResources(resourceBundle);
        loader.setLocation(getClass().getResource(fxmlPath));

        return loader.load();
    }
}
