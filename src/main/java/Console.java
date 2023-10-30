import java.util.Scanner;

public class Console {
    public static String ANSI_CLEAR_SCREEN = "\u001B[H\u001B[2J";
    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_RED = "\u001B[31m";
    public static String ANSI_GREEN = "\u001B[32m";
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_PURPLE = "\u001B[35m";
    public static String ANSI_CYAN = "\u001B[36m";
    public static String ANSI_WHITE = "\u001B[37m";
    public static String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void env(Table e, Player player) {
        System.out.print(ANSI_CLEAR_SCREEN);
        System.out.print(ANSI_WHITE + "It's ");
        System.out.print((player.getPiece() == 1 ? ANSI_BLUE : ANSI_RED) + player.getName());
        System.out.print(ANSI_WHITE + " turn.\n");
        printGameBoard(e);
        System.out.print(ANSI_RESET);
    }

    public static void env(Table e) {
        System.out.print(ANSI_CLEAR_SCREEN);
        printGameBoard(e);
        System.out.print(ANSI_RESET);
    }

    public static void printGameBoard(Table e) {
        short[][] env = e.getGameBoard();

        for (int i = 0; i < env.length; i++) {
            for (int j = 0; j < env[i].length; j++) {
                if (env[i][j] == 0) System.out.print(ANSI_PURPLE + " " + (i*3+j+1));
                else if (env[i][j] == 1) System.out.print(ANSI_BLUE + " O");
                else System.out.print(ANSI_RED + " X");
                if (j != env[i].length - 1) System.out.print(ANSI_GREEN + " #");
            }
            System.out.print("\n");
            if (i != env.length - 1)
                for (int j = 0; j < 11; j++) System.out.print(ANSI_GREEN + "#");
            System.out.print("\n");
        }
        System.out.print(ANSI_RESET);
    }

    public static boolean exitMessage() {
        System.out.print(ANSI_CLEAR_SCREEN + ANSI_YELLOW + "Do you really want to exit? :( (Y/N)\n>> ");
        System.out.print(ANSI_RESET);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y");
    }

    public static void exit() {
        System.out.print(ANSI_CLEAR_SCREEN + ANSI_CYAN + "Good bye my friend.");
        System.out.print(ANSI_RESET);

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        System.out.print(ANSI_CLEAR_SCREEN);
    }

    public static void winMessage(short player, Player player1, Player player2) {
        if (player == -1) System.out.print(ANSI_PURPLE + "Tha game is DRAW");
        if (player == player1.getPiece()) System.out.print(ANSI_BLUE + player1.getName() + ANSI_YELLOW + " is winner");
        if (player == player2.getPiece()) System.out.print(ANSI_RED + player2.getName() + ANSI_YELLOW + " is winner");
        System.out.print(ANSI_RESET);

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void help() {
        System.out.print(ANSI_CLEAR_SCREEN);
        System.out.print(ANSI_CYAN);
        System.out.print("/put n\nThis command put the player mark on place n.\n\n");
        System.out.print("/exit \nThis command end the game.");
        System.out.print(ANSI_RESET);

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void menu() {
        System.out.print(ANSI_CLEAR_SCREEN);
        System.out.print(ANSI_WHITE + "1. Single Player\n");
        System.out.print(ANSI_CYAN + "2. Two Player\n");
        System.out.print(ANSI_YELLOW + "3. Help\n");
        System.out.print(ANSI_RED + "4. Exit\n");
        System.out.print(ANSI_GREEN + ">> ");
        System.out.print(ANSI_RESET);
    }

    public static boolean endMessage() {
        System.out.print(ANSI_CLEAR_SCREEN + ANSI_CYAN + "Do you really want to end the game (all your progress will be deleted)? :( (Y/N)\n>> ");
        System.out.print(ANSI_RESET);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y");
    }

    public static void invalidInput() {
        System.out.print(ANSI_CLEAR_SCREEN + ANSI_RED + "Your input is invalid. Press enter to go back.");
        System.out.print(ANSI_RESET);

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static short chooseTurn() {
        System.out.print(ANSI_CLEAR_SCREEN + ANSI_YELLOW + "Do you want to be the first player? (Y/N)\n>> ");
        System.out.print(ANSI_RESET);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y") ? (short) 1 : (short) 2;
    }

    public static void showError(String error) {
        System.out.print(ANSI_CLEAR_SCREEN + ANSI_RED + error);
        System.out.print(ANSI_RESET);

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static String getPlayerName() {
        System.out.print(ANSI_CLEAR_SCREEN + ANSI_RESET + ANSI_CYAN + "Enter Your Name:\n" + ANSI_GREEN + ">> ");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine().trim();
        return name.equals("") ? "Player" : name;
    }

    public static String[] getPlayerNames() {
        Scanner scanner = new Scanner(System.in);

        System.out.print(ANSI_CLEAR_SCREEN + ANSI_RESET + ANSI_BLUE + "Enter First Player Name:\n" + ANSI_GREEN + ">> ");
        String player1Name = scanner.nextLine().trim();
        System.out.print(ANSI_CLEAR_SCREEN + ANSI_RESET + ANSI_RED + "Enter Second Player Name:\n" + ANSI_GREEN + ">> ");
        String player2Name = scanner.nextLine().trim();

        return new String[] {player1Name.equals("") ? "Player 1": player1Name, player2Name.equals("") ? "Player 2": player2Name};
    }
}
