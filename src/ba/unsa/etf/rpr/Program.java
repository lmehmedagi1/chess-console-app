package ba.unsa.etf.rpr;

import java.util.Scanner;

import ba.unsa.etf.rpr.ChessPiece.Color;

public class Program {
    static public void odigraj(Color boja, Board ploca) {
        Scanner ulaz = new Scanner(System.in);
        boolean ispravanPotez = false;

        final char[] figure = {'K', 'Q', 'R', 'B', 'N', 'P'};
        Class[] tip = new Class[]{King.class, Queen.class, Rook.class, Bishop.class, Knight.class, Pawn.class};
        int figura = 5;

        while (!ispravanPotez) {
            String potez = ulaz.nextLine();

            if (potez.equals("X")) {
                if (boja==Color.WHITE) { System.out.println("Bijeli predao. Pobjeda crnih!"); }
                else { System.out.println("Crni predao. Pobjeda bijelih!"); }
                System.exit(0);
            }

            try {
                if (potez.length() == 2) ploca.move(Pawn.class, boja, potez);
                else {
                    for (int i = 0; i < 6; i++) if (figure[i] == potez.charAt(0)) figura = i;
                    ploca.move(tip[figura], boja, potez.substring(1, 2));
                }
                ispravanPotez = true;
                if (boja==Color.WHITE) { if (ploca.isCheck(Color.BLACK)) System.out.println("CHECK!!!\n"); }
                else { if (ploca.isCheck(Color.WHITE)) System.out.println("CHECK!!!\n"); }
            } catch (Exception e) {
                System.out.println("Neispravan potez. Unesite ponovo: \n");
            }
        }
    }

    public static void main(String[] args) {
        Board ploca = new Board();

        for (; ; ) {
            System.out.println("White move: \n");
            odigraj(Color.WHITE, ploca);
            System.out.println("Black move: \n");
            odigraj(Color.BLACK, ploca);
        }
    }
}
