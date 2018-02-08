package nl.smit.scheduler.referee;

import nl.smit.scheduler.referee.helper.gui.TestFxBase;
import nl.smit.scheduler.referee.view.ViewFxml;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Example FX test.
 *
 * @author Jordi Smit, 8-2-2018.
 */
public class ExampleGUITest extends TestFxBase {

    @Test
    void test() {
        ensureEventQueueComplete();
        assertThat(getPrimaryStage().getTitle()).isEqualToIgnoringCase(ViewFxml.MAIN.getTitle());
    }
}
