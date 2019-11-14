package app;

import org.testng.annotations.*;
import static org.testng.Assert.*;

/**
 * App Unit Testing
 * 
 * @author Thiago Rezende
 */
public class AppTest {
    @Test
    public void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
