package lesson5;

import lesson5.exceptions.AccountIsLockedException;
import lesson5.exceptions.NotAuthenticatedException;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ivan on 24/09/16.
 */
public class PinValidator {
    private final String pin;
    private int nCountEntered;
    private boolean pinEntered;
    private Date unlockDate = null;

    // pin - правильный pin
    public PinValidator(String pin) {
        this.pin = pin;
    }

    // проверить входной pin на соответствие указанному при создании объекта пина
    public boolean isValid(String pin) throws AccountIsLockedException {
        throwOnLock();

        if (pin.compareTo(this.pin) != 0) {

            if (++nCountEntered == 3) {
                nCountEntered = 0;
                lock();
            }

            pinEntered = false;
            return false;
        }

        pinEntered = true;
        return true;
    }

    public void ensureAuth() throws AccountIsLockedException, NotAuthenticatedException {
        throwOnLock();
        if (!pinEntered)
            throw new NotAuthenticatedException();
    }

    // выкидывает exception если аккаунт заблокирован
    public void throwOnLock() throws AccountIsLockedException {
        if (isLocked())
            throw new AccountIsLockedException(unlockDate);
    }

    // проверить состояние блокировки
    public boolean isLocked() {
        if (unlockDate == null || unlockDate.compareTo(new Date()) <= 0) {
            unlockDate = null;
            return false;
        }
        return true;
    }

    private void lock() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 5);
        unlockDate = c.getTime();
    }
}
