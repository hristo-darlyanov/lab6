package client.utilities;

import java.util.Scanner;

public class Console {
    private static Scanner scanner = new Scanner(System.in);
    private Console() {
    }

    private static void resetScanner(){
        System.out.println("\nConsole reset. Please type your input again.");
        scanner = new Scanner(System.in);
    }

    /** 
     * @return String
     */
    public static String readLineConsole() {
        String input = "";
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                resetScanner();
                continue;
            }
            input = scanner.nextLine();
            break;
        }

        return input;
    }

    public static String promptReadLine(String prompt) {
        String input = "";
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                resetScanner();
                continue;
            }
            input = scanner.nextLine();
            break;
        }

        return input;
    }
}
