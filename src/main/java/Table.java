public class Table {
    private short[][] gameBoard;

    Table() {
        gameBoard = new short[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    }

    public short[][] getGameBoard() {
        return gameBoard;
    }

    public String put(int x, int y, Player player) {
        if (gameBoard[y][x] == 0) {
            gameBoard[y][x] = player.getPiece();
            return "";
        } else {
            return "Selected point is already full.";
        }
    }

    private boolean playerIsWinner(short player) {
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i][0] == player && gameBoard[i][1] == player && gameBoard[i][2] == player) return true;
        }
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[0][i] == player && gameBoard[1][i] == player && gameBoard[2][i] == player) return true;
        }
        if (gameBoard[0][0] == player && gameBoard[1][1] == player && gameBoard[2][2] == player) return true;
        if (gameBoard[0][2] == player && gameBoard[1][1] == player && gameBoard[2][0] == player) return true;
        return false;
    }

    private boolean isDraw() {
        for (int i = 0; i < gameBoard.length; i++)
            for (int j = 0; j < gameBoard[i].length; j++)
                if (gameBoard[i][j] == 0) return false;
        return true;
    }

    public short thereIsWinner() {
        if (playerIsWinner((short) 1)) return 1;
        if (playerIsWinner((short) 2)) return 2;
        if (isDraw()) return -1;
        return 0;
    }

    public boolean isBlank() {
        for (int i = 0; i < gameBoard.length; i++)
            for (int j = 0; j < gameBoard[i].length; j++)
                if (gameBoard[i][j] != 0) return false;
        return true;
    }
}
