package lesson5.exceptions;

/**
 * Created by Ivan on 24/09/16.
 */
public class NotAuthenticatedException extends TerminalException {

    @Override
    public String getMessage() {
        return "не введен pin код";
    }
}
