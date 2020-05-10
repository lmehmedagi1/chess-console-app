package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.ChessPiece.Color;

public class Board {
    ChessPiece[][] ploca;

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

    private boolean preskace(ChessPiece figura, String position) {
        if (figura instanceof Knight || figura instanceof King) return false;

        if (figura instanceof Queen || figura instanceof Rook) {
            if (kolona(position)==kolona(figura.getPosition())) {
                for (int i=Math.max(red(position), red(figura.getPosition()))-2; i>Math.min(red(position), red(figura.getPosition()))-1; i--)
                    if (!(ploca[i][kolona(position)-1] instanceof PraznoPolje)) return true;
                return false;
            }
            else if (red(position)==red(figura.getPosition())) {
                for (int i=Math.max(kolona(position), kolona(figura.getPosition()))-2; i>Math.min(kolona(position)-1, kolona(figura.getPosition())); i--)
                    if (!(ploca[red(position)-1][i] instanceof PraznoPolje)) return true;
                return false;
            }
        }

        if (figura instanceof Queen || figura instanceof Bishop) {
            if (kolona(figura.getPosition())-red(figura.getPosition())==kolona(position)-red(position))  {
                for (int i=Math.max(kolona(position),kolona(figura.getPosition()))-2; i>Math.min(kolona(position),kolona(figura.getPosition()))-1; i--)
                    if (!(ploca[i-kolona(position)+red(position)][i] instanceof PraznoPolje)) return true;
            }
            else if (kolona(figura.getPosition())+red(figura.getPosition())==kolona(position)+red(position)) {
                for (int i = Math.max(kolona(position), kolona(figura.getPosition())) - 2; i > Math.min(kolona(position), kolona(figura.getPosition())) - 1; i--)
                    if (!(ploca[kolona(position)+red(position)-2-i][i] instanceof PraznoPolje)) return true;
            }
        }

        if (figura instanceof Pawn && Math.abs(red(position)-red(figura.getPosition()))==2) {
            if (!(ploca[(red(position)+red(figura.getPosition()))/2-1][kolona(position)-1] instanceof PraznoPolje)) return true;
        }

        return false;
    }

    Board() {
        ploca=new ChessPiece[8][8];

        ploca[0][0]=new Rook("A1", Color.WHITE);
        ploca[0][1]=new Knight("B1", Color.WHITE);
        ploca[0][2]=new Bishop("C1", Color.WHITE);
        ploca[0][4]=new King("E1", Color.WHITE);
        ploca[0][3]=new Queen("D1", Color.WHITE);
        ploca[0][5]=new Bishop("F1", Color.WHITE);
        ploca[0][6]=new Knight("G1", Color.WHITE);
        ploca[0][7]=new Rook("H1", Color.WHITE);

        for (int i=0; i<8; i++) ploca[1][i]=new Pawn((char)('A'+i)+"2", Color.WHITE);

        ploca[7][0]=new Rook("A8", Color.BLACK);
        ploca[7][1]=new Knight("B8", Color.BLACK);
        ploca[7][2]=new Bishop("C8", Color.BLACK);
        ploca[7][4]=new King("E8", Color.BLACK);
        ploca[7][3]=new Queen("D8", Color.BLACK);
        ploca[7][5]=new Bishop("F8", Color.BLACK);
        ploca[7][6]=new Knight("G8", Color.BLACK);
        ploca[7][7]=new Rook("H8", Color.BLACK);

        for (int i=0; i<8; i++) ploca[6][i]=new Pawn((char)('A'+i)+"7", Color.BLACK);

        for (int i=2; i<6; i++) {
            for (int j=0; j<8; j++) {
                ploca[i][j]=new PraznoPolje((char)('A'+j)+Integer.toString(i+1), Color.WHITE);
            }
        }
    }

    public void move(Class type, Color color, String position) {

        provjeriJeLiValidnaPozicija(position);
        String nazivKlase=type.getSimpleName();

        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (ploca[i][j].getClass().getSimpleName().equals(nazivKlase)  && (ploca[i][j]).getColor()==color) {
                    if (ploca[i][j].mozeLiNaPoziciju(position)) {
                        if (preskace(ploca[i][j], position)) continue;
                        else if (ploca[red(position)-1][kolona(position)-1] instanceof PraznoPolje) {
                            if (ploca[i][j] instanceof Pawn) {
                                if (kolona(ploca[i][j].getPosition())==kolona(position)) ploca[i][j].move(position);
                                else continue;
                            }
                            else ploca[i][j].move(position);
                        }
                        else if (ploca[red(position)-1][kolona(position)-1].getColor()==color) throw new IllegalChessMoveException("Figura iste boje");
                        else {
                            if (!(ploca[red(position)-1][kolona(position)-1] instanceof King)) {
                                if (ploca[i][j] instanceof Pawn) {
                                    if (kolona(ploca[i][j].getPosition()) != kolona(position))
                                        ploca[i][j].move(position);
                                    else continue;
                                } else ploca[i][j].move(position);
                            }
                            else continue;
                        }
                        ploca[red(position)-1][kolona(position)-1]=ploca[i][j];
                        ploca[i][j]=new PraznoPolje((char)('A'+j)+Integer.toString(i+1), Color.WHITE);
                        return;
                    }
                }
            }
        }
        throw new IllegalChessMoveException("Nije pronadjen legalan potez");
    }

    public void move(String oldPosition, String newPosition) {

        provjeriJeLiValidnaPozicija(oldPosition);
        provjeriJeLiValidnaPozicija(newPosition);

        if (ploca[red(oldPosition)-1][kolona(oldPosition)-1] instanceof PraznoPolje) throw new IllegalArgumentException("Nije pronadjena figura na poziciji");

        if (!ploca[red(oldPosition)-1][kolona(oldPosition)-1].mozeLiNaPoziciju(newPosition)) throw new IllegalChessMoveException("Nemoguc potez");
        if (preskace(ploca[red(oldPosition)-1][kolona(oldPosition)-1], newPosition)) throw new IllegalChessMoveException("Figura ne moze preskakati ostale");

        if (ploca[red(newPosition)-1][kolona(newPosition)-1] instanceof PraznoPolje) {

            if (ploca[red(oldPosition)-1][kolona(oldPosition)-1] instanceof Pawn) {
                if (kolona(oldPosition)==kolona(newPosition)) ploca[red(oldPosition)-1][kolona(oldPosition)-1].move(newPosition);
                else throw new IllegalChessMoveException("Nemoguc potez");
            }
            else ploca[red(oldPosition)-1][kolona(oldPosition)-1].move(newPosition);
        }
        else if (ploca[red(oldPosition)-1][kolona(oldPosition)-1].getColor()==ploca[red(newPosition)-1][kolona(newPosition)-1].getColor()) throw new IllegalChessMoveException("Figura iste boje");
        else {
            if (!(ploca[red(newPosition)-1][kolona(newPosition)-1] instanceof King)) {
                if (ploca[red(oldPosition) - 1][kolona(oldPosition) - 1] instanceof Pawn) {
                    if (kolona(oldPosition) != kolona(newPosition))
                        ploca[red(oldPosition) - 1][kolona(oldPosition) - 1].move(newPosition);
                    else throw new IllegalChessMoveException("Nemoguc potez");
                } else ploca[red(oldPosition) - 1][kolona(oldPosition) - 1].move(newPosition);
            }
            else throw new IllegalChessMoveException("Nije moguce pojesti kralja");
        }

        ploca[red(newPosition)-1][kolona(newPosition)-1]=ploca[red(oldPosition)-1][kolona(oldPosition)-1];
        ploca[red(oldPosition)-1][kolona(oldPosition)-1]=new PraznoPolje(((char)('A'+kolona(oldPosition)-1))+Integer.toString(red(oldPosition)), Color.WHITE);
    }

    public boolean isCheck(Color boja) {
        String pozicijaKralja="";

        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (ploca[i][j] instanceof King && ploca[i][j].getColor()==boja) {
                    pozicijaKralja=ploca[i][j].getPosition();
                    break;
                }
            }
        }

        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (ploca[i][j].getColor()!=boja && !(ploca[i][j] instanceof PraznoPolje)) {
                    if (ploca[i][j].mozeLiNaPoziciju(pozicijaKralja) && !preskace(ploca[i][j], pozicijaKralja)) return true;
                }
            }
        }
        return false;
    }

}
