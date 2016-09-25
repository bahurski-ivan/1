package lesson5;

/**
 * Created by Ivan on 24/09/16.
 */
public class Homework {

    public static final String PIN = "1111";
    public static final int INITIAL_BALANCE = 370;

    public static void main(String[] args) {
        TerminalManipulator manipulator = new ConsoleTerminalManipulator();
        TerminalServer server = new TerminalServerImpl(INITIAL_BALANCE);
        Terminal terminal = new TerminalImpl(PIN, server);

        TerminalUserSession session = new TerminalUserSession(terminal, manipulator);

        session.run();
    }

}
