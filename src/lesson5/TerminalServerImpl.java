package lesson5;

import lesson5.exceptions.BadMoneyAmountException;
import lesson5.exceptions.MaintenanceWorkInProgressException;
import lesson5.exceptions.NotEnoughMoneyException;
import lesson5.exceptions.TerminalServerBusyException;

import java.util.Random;

/**
 * Created by Ivan on 24/09/16.
 */
public class TerminalServerImpl implements TerminalServer {

    private int balance;

    public TerminalServerImpl(int balance) {
        this.balance = balance;
    }

    @Override
    public void get(int amount) throws NotEnoughMoneyException, BadMoneyAmountException,
            MaintenanceWorkInProgressException, TerminalServerBusyException {
        makeRandomException();

        if (amount % 100 != 0)
            throw new BadMoneyAmountException(amount);

        if (balance < amount)
            throw new NotEnoughMoneyException();

        balance -= amount;
    }

    @Override
    public void put(int amount) throws BadMoneyAmountException, MaintenanceWorkInProgressException,
            TerminalServerBusyException {
        makeRandomException();

        if (amount % 100 != 0)
            throw new BadMoneyAmountException(amount);

        balance += amount;
    }

    @Override
    public int balance() throws MaintenanceWorkInProgressException, TerminalServerBusyException {
        makeRandomException();

        return balance;
    }

    // исскусственные exception
    private void makeRandomException() throws TerminalServerBusyException, MaintenanceWorkInProgressException {
        int rand = new Random().nextInt(1000);

        switch (rand) {
            case 34:
                throw new TerminalServerBusyException();
            case 499:
                throw new MaintenanceWorkInProgressException();
        }

    }
}
