package lesson5;

import lesson5.exceptions.BadMoneyAmountException;
import lesson5.exceptions.MaintenanceWorkInProgressException;
import lesson5.exceptions.NotEnoughMoneyException;
import lesson5.exceptions.TerminalServerBusyException;

/**
 * Created by Ivan on 24/09/16.
 */
public interface TerminalServer {
    // снять деньги со счета
    void get(int amount) throws NotEnoughMoneyException, BadMoneyAmountException,
            MaintenanceWorkInProgressException, TerminalServerBusyException;

    // положить деньги на счет
    void put(int amount) throws BadMoneyAmountException, MaintenanceWorkInProgressException,
            TerminalServerBusyException;

    // возвращает текущий баланс
    int balance() throws MaintenanceWorkInProgressException, TerminalServerBusyException;
}
