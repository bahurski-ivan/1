package lesson5.exceptions;

/**
 * Created by Ivan on 24/09/16.
 */
public class NotEnoughMoneyException extends TerminalException {
    @Override
    public String getMessage() {
        return "недостаточно средств для проведения операции";
    }
}
