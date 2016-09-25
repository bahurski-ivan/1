package lesson5;

import lesson5.exceptions.*;

/**
 * Created by Ivan on 24/09/16.
 */
public interface Terminal {
    // возвращает текущий баланс
    int balance() throws AccountIsLockedException, MaintenanceWorkInProgressException,
            NotAuthenticatedException, TerminalServerBusyException;

    // положить деньги на счет
    void put(int amount) throws AccountIsLockedException, MaintenanceWorkInProgressException,
            NotAuthenticatedException, TerminalServerBusyException, BadMoneyAmountException;

    // снять деньги со счета
    void get(int amount) throws AccountIsLockedException, MaintenanceWorkInProgressException,
            NotAuthenticatedException, TerminalServerBusyException, BadMoneyAmountException, NotEnoughMoneyException;

    // авторизация с помощью pin кода
    boolean login(String pin) throws AccountIsLockedException;
}
