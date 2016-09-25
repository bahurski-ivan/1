package lesson5.exceptions;

/**
 * Created by Ivan on 24/09/16.
 */
public class MaintenanceWorkInProgressException extends TerminalException {

    @Override
    public String getMessage() {
        return "проводяться регламентные работы, повторите свой запрос позже";
    }
}
