package lesson5;

/**
 * Created by Ivan on 24/09/16.
 */
public interface TerminalManipulator {
    // установить объект принимающий действие которое нужно осуществить
    void setActionReceiver(TerminalAction ta);

    // ожидать команды от пользователя
    boolean waitForCommand();

    // вывод сообщения
    void onMessage(String message);

    // вывод сообщения об ошибке
    void onError(String message);
}
