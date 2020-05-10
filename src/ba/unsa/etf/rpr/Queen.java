package ba.unsa.etf.rpr;

public class Queen extends ChessPiece {
    String pozicija;
    Color boja;

    private int red (String s) {
        return Integer.parseInt(s.substring(1,2));
    }
    private int kolona (String s) {
        if (s.charAt(0)<'a') return s.charAt(0)%64;
        return s.charAt(0)%96;
    }
    private void provjeriJeLiValidnaPozicija (String position) {
        if (position.length() != 2) throw new IllegalArgumentException("Ilegalan format pozicije");
        if (!(((position.charAt(0) >= 'A' && position.charAt(0) <= 'H') || (position.charAt(0) >= 'a' && position.charAt(0) <= 'h')) && (position.charAt(1) >= '1' && position.charAt(1) <= '8')))
            throw new IllegalArgumentException("Pozicija van table");
    }

    Queen (String position, Color color) {
        provjeriJeLiValidnaPozicija(position);
        pozicija=position;
        boja=color;
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
    public boolean mozeLiNaPoziciju(String position) {
        return (kolona(pozicija)==kolona(position) || red(pozicija)==red(position) || kolona(pozicija)-red(pozicija)==kolona(position)-red(position) || kolona(pozicija)+red(pozicija)==kolona(position)+red(position));
    }

    @Override
    public void move (String position) {
        provjeriJeLiValidnaPozicija(position);
        if (!mozeLiNaPoziciju(position)) throw new IllegalChessMoveException("Ilegalan potez");

        pozicija=position;
    }
}
