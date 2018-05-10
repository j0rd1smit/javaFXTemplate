package nl.smit.javafx_spring.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;
import nl.smit.javafx_spring.view.StageManager;
import nl.smit.javafx_spring.view.ViewFxml;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * The main controller.
 *
 * @author Jordi Smit, 8-2-2018.
 */
@Component
@RequiredArgsConstructor(onConstructor = @__({ @Autowired, @Lazy}))
public class MainController implements FxmlController {
    private static final Logger LOG = getLogger(MainController.class);

    private final StageManager stageManager;

    @FXML
    private JFXButton button;

    @Override
    public void initialize() {
        LOG.info("Main controller");
        button.setOnAction(e -> stageManager.switchScene(ViewFxml.NOT_MAIN));
    }
}