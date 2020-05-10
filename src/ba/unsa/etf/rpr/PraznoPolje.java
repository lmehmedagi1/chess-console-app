package ba.unsa.etf.rpr;

public class PraznoPolje extends ChessPiece {
    String pozicija;
    Color boja;

    PraznoPolje (String position, Color color) {
        pozicija=position; boja=color;
    }

    @Override
    public String getPosition() {
        return pozicija;
    }

    @Override
    public Color getColor() {
        return boja;
    }

    @Override
    public void move(String position) {}

    @Override
    public boolean mozeLiNaPoziciju(String position) { return true; }
}
