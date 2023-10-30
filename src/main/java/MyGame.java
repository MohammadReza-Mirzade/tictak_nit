import java.util.Scanner;
import java.util.regex.Pattern;

public class MyGame {
    public static boolean isEnded = true;
    public static boolean exit = false;
    public static String error = "";

    public static void endGame() {
        isEnded = true;
    }
    public static void exitGame() { exit = true; }

    public static void menu() {
        while (!exit) {
            Console.menu();
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    startSinglePlayer();
                    break;
                case 2:
                    startTwoPlayer();
                    break;
                case 3:
                    Console.help();
                    break;
                case 4:
                    if (Console.exitMessage()) {
                        Console.exit();
                        exitGame();
                    }
                    break;
                default:
                    Console.invalidInput();
            }
        }
    }

    private static void startSinglePlayer() {
        Table env = new Table();
        short turn = 1;
        isEnded = false;

        Player player = new Player(Console.chooseTurn(), Console.getPlayerName());
        Bot bot = new Bot(player.getPiece() == 1 ? (short) 2 : (short) 1, env);

        while (!isEnded) {
            Console.env(env, player);
            if (player.getPiece() == turn) {
                turn = playerMove(env, player, bot);
            } else {
                bot.move();
                turn = turn == 1 ? (short)2 : (short)1;
                if (env.thereIsWinner() != 0) {
                    Console.env(env);
                    Console.winMessage(env.thereIsWinner(), bot, player);
                    endGame();
                }
            }

        }
    }

    private static short playerMove(Table env, Player player, Player otherPlayer) {
        short turn = player.getPiece();
        if (error.length() != 0) {
            Console.showError(error);
            error = "";
        }
        Scanner scanner = new Scanner(System.in);
        Pattern p = Pattern.compile("^/put\s[1-9]$");
        System.out.print(">> ");
        String input = scanner.nextLine().trim().toLowerCase();
        switch (input) {
            case "/help":
                Console.help();
                break;
            case "/exit":
                if (Console.endMessage()) {
                    endGame();
                }
                break;
            default:
                if (p.matcher(input).matches()) {
                    int place = Integer.valueOf(input.split(" ")[1]) - 1;
                    error = env.put(place%3, place/3, player);
                    if (error.length() == 0)
                        turn = turn == 1 ? (short)2 : (short)1;
                    if (env.thereIsWinner() != 0) {
                        Console.env(env, player);
                        Console.winMessage(env.thereIsWinner(), player, otherPlayer);
                        endGame();
                    }
                }
        }
        return turn;
    }

    private static void startTwoPlayer() {
        Table env = new Table();
        short turn = 1;
        String[] names = Console.getPlayerNames();
        Player player1 = new Player((short) 1, names[0]);
        Player player2 = new Player((short) 2, names[1]);
        isEnded = false;

        while (!isEnded) {
            Console.env(env, turn == 1 ? player1 : player2);
            turn = playerMove(env, turn == 1 ? player1 : player2, turn == 1 ? player2 : player1);
        }
    }


    public static void main(String[] args) {
        menu();
        return;
    }
}

