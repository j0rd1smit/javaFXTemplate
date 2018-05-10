package nl.smit.javafx_spring.referee;

import nl.smit.javafx_spring.SimpleApp;
import nl.smit.javafx_spring.referee.helper.gui.TestFxBase;
import nl.smit.javafx_spring.view.ViewFxml;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Example FX test.
 *
 * @author Jordi Smit, 8-2-2018.
 */
public class ExampleGUITest extends TestFxBase {


    @BeforeAll
    static void beforeAll() {
        displayGUIDuringTest();
    }

    @Override
    protected  Class<SimpleApp> getApplicationClass() {
        return SimpleApp.class;
    }

    @Test
    void test() throws InterruptedException {
        ensureEventQueueComplete();
        assertThat(getPrimaryStage().getTitle()).isEqualToIgnoringCase(ViewFxml.MAIN.getTitle());
    }
}
