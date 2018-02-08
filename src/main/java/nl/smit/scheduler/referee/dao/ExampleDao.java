package nl.smit.scheduler.referee.dao;

import nl.smit.scheduler.referee.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * An example dao.
 *
 * @author Jordi Smit, 8-2-2018.
 */
public interface ExampleDao extends JpaRepository<Example, UUID> {

    /**
     *
     * @param name The name.
     * @return A list of examples with the same name.
     */
    List<Example> findByName(String name);
}
