package lesson5;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Ivan on 25/09/16.
 */
public class ConsoleTerminalManipulator implements TerminalManipulator {
    private TerminalAction action;
    private boolean needToPrintInfo = true;

    @Override
    public void setActionReceiver(TerminalAction action) {
        this.action = action;
    }

    @Override
    public boolean waitForCommand() {

        printInfo();

        String command;
        boolean done, returnValue = true;
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));

        do {
            done = true;

            System.out.print("> ");

            try {
                command = br.readLine();
            } catch (IOException e) {
                System.out.println("Ошибка чтения команды, пожалуйста повторите свой запрос");
                done = false;
                continue;
            }

            String[] tokens = command.split(" ");

            if (tokens.length == 0) {
                done = false;
                continue;
            }

            switch (tokens[0].toLowerCase()) {
                case "pin": {
                    String pin = null;

                    if (tokens.length != 2 || (pin = tokens[1].trim()).length() == 0) {
                        System.out.println("Команда не соответствует формату \"pin <pin-code>\"");
                        done = false;
                    } else
                        action.performLogin(pin);

                }
                break;

                case "put": {

                    Integer sum = getInteger(tokens, 1);

                    if (sum == null) {
                        System.out.println("Команда не соответствует формату \"put <sum>\"");
                        done = false;
                    } else
                        action.performPut(sum);

                }
                break;

                case "get": {

                    Integer sum = getInteger(tokens, 1);

                    if (sum == null) {
                        System.out.println("Команда не соответствует формату \"get <sum>\"");
                        done = false;
                    } else
                        action.performGet(sum);

                }
                break;

                case "balance":
                    action.performBalance();
                    break;

                case "exit":
                    returnValue = false;
                    break;

                default:
                    System.out.println("Неизвестная команда");
                    done = false;
            }

        } while (!done);

        return returnValue;
    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void onError(String message) {
        System.out.println(message);
    }

    private Integer getInteger(String[] tokens, int pos) {
        Integer result = null;

        if (tokens.length > pos) {
            try {
                result = Integer.parseInt(tokens[pos]);
            } catch (NumberFormatException e) {
                result = null;
            }
        }

        return result;
    }

    private void printInfo() {
        if (needToPrintInfo) {
            System.out.println("Комманды терминала: ");
            System.out.println(" pin <pin-code> - для ввода pin кода");
            System.out.println(" get <sum>      - снять некоторую сумму денег");
            System.out.println(" put <sum>      - положить некоторую сумму денег");
            System.out.println(" balance        - вывести свой баланс");
            System.out.println(" exit           - закончить работу с терминалом");
            System.out.println();

            needToPrintInfo = false;
        }
    }
}
