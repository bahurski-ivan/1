package lesson5.exceptions;

import java.util.Date;

/**
 * Created by Ivan on 24/09/16.
 */
public class AccountIsLockedException extends TerminalException {

    private Date unlockTime;

    public AccountIsLockedException(Date unlockTime) {
        this.unlockTime = unlockTime;
    }

    @Override
    public String getMessage() {
        return "pin код введен неверно 3 раза, терминал заблокирован (дата разблокировки - " + unlockTime + ")";
    }
}
