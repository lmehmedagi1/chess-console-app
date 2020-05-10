package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @org.junit.jupiter.api.Test
    void move1() {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> p.move("E4")
        );
    }

    @org.junit.jupiter.api.Test
    void moveIllegal() {
        Pawn p = new Pawn("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> p.move("i1")
        );
    }

    @org.junit.jupiter.api.Test
    void moveIllegal2() {
        Pawn p = new Pawn("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("d1")
        );
    }
}