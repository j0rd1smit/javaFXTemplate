package nl.smit.scheduler.referee;


import nl.smit.scheduler.referee.dao.ExampleDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Example spring text.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RefereeApplicationTests {
    @Autowired
    @SuppressWarnings("nullness")
	private ExampleDao exampleDao;

	@Test
	public void contextLoads() {
	}
}
