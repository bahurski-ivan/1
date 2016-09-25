package lesson5.exceptions;

/**
 * Created by Ivan on 24/09/16.
 */
public class TerminalServerBusyException extends TerminalException {

    @Override
    public String getMessage() {
        return "большая нагрузка на сервер, повторите свой запрос позже";
    }
}
