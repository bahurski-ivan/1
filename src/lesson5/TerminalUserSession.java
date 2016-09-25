package lesson5;

import lesson5.exceptions.TerminalException;

/**
 * Created by Ivan on 24/09/16.
 */
public class TerminalUserSession implements TerminalAction, Runnable {

    private Terminal terminal;
    private TerminalManipulator manipulator;

    public TerminalUserSession(Terminal terminal, TerminalManipulator manipulator) {
        this.terminal = terminal;
        this.manipulator = manipulator;

        manipulator.setActionReceiver(this);
    }

    @Override
    public void performGet(int amount) {

        try {
            terminal.get(amount);
        } catch (TerminalException e) {
            manipulator.onError("Не удалось осуществить списание денег: " + e.getMessage());
        }

    }

    @Override
    public void performPut(int amount) {

        try {
            terminal.put(amount);
        } catch (TerminalException e) {
            manipulator.onError("Ошибка пополнения баланса: " + e.getMessage());
        }

    }

    @Override
    public void performLogin(String pin) {

        try {
            if (terminal.login(pin))
                manipulator.onMessage("Pin код верный, взаимодействие с терминалом разрешено");
            else
                manipulator.onMessage("Неправильный pin код");
        } catch (TerminalException e) {
            manipulator.onError("Ошибка: " + e.getMessage());
        }

    }

    @Override
    public void performBalance() {

        try {
            int balance = terminal.balance();

            manipulator.onMessage("Баланс -- " + balance);
        } catch (TerminalException e) {
            manipulator.onError("Ошибка получения баланса: " + e.getMessage());
        }

    }

    @Override
    public void run() {
        while (manipulator.waitForCommand()) ;
    }
}
