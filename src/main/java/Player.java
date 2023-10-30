public class Player {
    private short piece;
    private String name;

    Player(short piece, String name) {
        this.piece = piece;
        this.name = name;
    }

    public short getPiece() {
        return piece;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPiece(short piece) {
        this.piece = piece;
    }
}
