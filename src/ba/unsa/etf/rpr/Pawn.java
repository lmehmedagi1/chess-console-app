package ba.unsa.etf.rpr;

public class Pawn extends ChessPiece {
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

    Pawn (String position, Color color)  {
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
        if (getColor()==Color.WHITE && kolona(getPosition())==kolona(position) && red(position)==4 && red(getPosition())==2) return true;
        if (getColor()==Color.BLACK && kolona(getPosition())==kolona(position) && red(position)==5 && red(getPosition())==7) return true;
        if (getColor()==Color.WHITE && (red(pozicija)+1!=red(position) || Math.abs(kolona(position)-kolona(pozicija))>1)) return false;
        if (getColor()==Color.BLACK && (red(pozicija)-1!=red(position) || Math.abs(kolona(position)-kolona(pozicija))>1)) return false;
        return true;
    }

    @Override
    public void move (String position) {
        provjeriJeLiValidnaPozicija(position);
        if (!mozeLiNaPoziciju(position)) throw new IllegalChessMoveException("Ilegalan potez");

        pozicija=position;
    }
}
