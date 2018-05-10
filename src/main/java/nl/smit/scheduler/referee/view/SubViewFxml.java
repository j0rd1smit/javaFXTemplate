package nl.smit.scheduler.referee.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Responsible for keeping track of the path to subview file.
 *
 * @author Jordi Smit, 8-2-2018.
 */
@AllArgsConstructor
public enum SubViewFxml implements ISubViewFxml {

    EXAMPLE("");

    @Getter
    private final String filePath;
}
