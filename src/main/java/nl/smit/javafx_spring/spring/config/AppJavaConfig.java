package nl.smit.javafx_spring.spring.config;

import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import nl.smit.javafx_spring.view.StageManager;
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
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class AppJavaConfig {
    private final SpringFxmlLoader springFxmlLoader;


    @Bean
    @Lazy(value = true)
    public StageManager stageManager(Stage stage) {
        return new StageManager(stage, springFxmlLoader);
    }
}
