package exceptions;

/**
 * IncompleteCourseException
 */
public class IncompleteCourseException extends Exception {
    private static final long serialVersionUID = 1L;

    public IncompleteCourseException(String message) {
        super(message);
    }

}