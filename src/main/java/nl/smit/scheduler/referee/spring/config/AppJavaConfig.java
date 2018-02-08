package nl.smit.scheduler.referee.spring.config;

import javafx.stage.Stage;
import nl.smit.scheduler.referee.view.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ResourceBundle;

/**
 * Responsible for declaring the default bean that spring will use.
 *
 * @author Jordi Smit, 8-2-2018.
 */
@Configuration
public class AppJavaConfig {
    @Autowired
    @SuppressWarnings("nullness")
    private SpringFxmlLoader springFxmlLoader;

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

    @Bean
    @Lazy(value = true)
    public StageManager stageManager(Stage stage) {
        return new StageManager(stage, springFxmlLoader);
    }
}
