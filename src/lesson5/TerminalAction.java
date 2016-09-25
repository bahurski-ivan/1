package lesson5;

/**
 * Created by Ivan on 24/09/16.
 */
public interface TerminalAction {
    void performGet(int amount);

    void performPut(int amount);

    void performLogin(String pin);

    void performBalance();
}
