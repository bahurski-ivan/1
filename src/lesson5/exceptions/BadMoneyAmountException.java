package lesson5.exceptions;

/**
 * Created by Ivan on 24/09/16.
 */
public class BadMoneyAmountException extends TerminalException {
    private int amount;

    public BadMoneyAmountException(int amount) {
        this.amount = amount;
    }

    @Override
    public String getMessage() {
        return "сумма ввода/вывода денег должна быть кратна 100 (вы ввели - " + amount + ")";
    }
}