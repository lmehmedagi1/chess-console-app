package ba.unsa.etf.rpr;

public abstract class ChessPiece {

    public static enum Color {WHITE, BLACK};

    public abstract String getPosition();
    public abstract Color getColor();
    public abstract void move(String position);
    public abstract boolean mozeLiNaPoziciju(String position);
}
