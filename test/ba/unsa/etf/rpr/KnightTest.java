package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void move() {
        Knight k = new Knight("B1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> k.move("C3")
        );

    }

    @Test
    void moveIllegal() {
        Knight k = new Knight("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> k.move("i1")
        );
    }

    @Test
    void moveIllegal2() {
        Knight k = new Knight("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalChessMoveException.class,
                () -> k.move("c2")
        );
    }

    @Test
    void getPosition() {
        Knight k = new Knight("C1", ChessPiece.Color.BLACK);
        assertEquals("C1", k.getPosition());
    }
}