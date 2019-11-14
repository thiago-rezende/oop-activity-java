package app;

/**
 * @author Thiago Rezende
 */
public class App {

    /**
     * @return String "Hello world."
     */
    public String getGreeting() {
        return "Hello world.";
    }

    /**
     * @param args launch options
     */
    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
