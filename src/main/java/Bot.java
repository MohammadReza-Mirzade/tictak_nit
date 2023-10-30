public class Bot extends Player {
    Table myEnv;
    private static final String[] BOT_NAMES = {"Mohammad Bot", "Ali Bot", "Reza Bot", "Harry Bot", "Ahmad Bot", "Akbar Bot"};

    Bot(short piece, Table env) {
        super(piece, BOT_NAMES[(int) Math.floor(Math.random() * BOT_NAMES.length)]);
        myEnv = env;
    }

    public int minimax(short[][] board, int depth, Boolean isMax) {
        short winner = myEnv.thereIsWinner();
        short playerTurn;

        if (getPiece()==1) playerTurn = 2;
        else playerTurn = 1;

        if (winner == getPiece()) return 1;
        if (winner == -1) return 0;
        if (winner != 0) return -1;

        int best;
        if (isMax) {
            best = -1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = getPiece();
                        best = Math.max(best, minimax(board, depth+1, !isMax));
                        board[i][j] = 0;
                    }
                }
            }
        } else {
            best = 1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = playerTurn;
                        best = Math.min(best, minimax(board, depth+1, !isMax));
                        board[i][j] = 0;
                    }
                }
            }
        }
        return best;
    }

    public void move() {
        int bestVal = -1000;
        int rowMove = -1;
        int colMove = -1;
        short[][] board = myEnv.getGameBoard().clone();

        if (board[1][1] == 0) {
            myEnv.put(1, 1, this);
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = getPiece();
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = 0;
                    if (moveVal > bestVal) {
                        rowMove = i;
                        colMove = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        myEnv.put(colMove, rowMove, this);
    }
}
