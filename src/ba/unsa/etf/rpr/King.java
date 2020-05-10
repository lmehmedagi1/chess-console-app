package ba.unsa.etf.rpr;

public class King extends ChessPiece {
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

    King (String position, Color color) {
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
        return !(Math.abs(red(pozicija)-red(position))>1 || Math.abs(kolona(pozicija)-kolona(position))>1);
    }

    @Override
    public void move (String position) {
        provjeriJeLiValidnaPozicija(position);
        if (!mozeLiNaPoziciju(position)) throw new IllegalChessMoveException("Ilegalan potez");

        pozicija=position;
    }
}
