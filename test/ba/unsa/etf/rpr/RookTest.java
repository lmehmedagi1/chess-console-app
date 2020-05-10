package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void mozeLiNaPoziciju() {
        Rook r=new Rook("A1", ChessPiece.Color.WHITE);
        assertTrue(r.mozeLiNaPoziciju("F1"));
    }

    @Test
    void mozeLiNaPoziciju2() {
        Rook r=new Rook("A1", ChessPiece.Color.WHITE);
        assertFalse(r.mozeLiNaPoziciju("b2"));
    }

    @Test
    void move() {
        Rook r=new Rook("A1", ChessPiece.Color.WHITE);
        assertThrows(
                IllegalArgumentException.class,
                () -> r.move("i2")
        );
    }

    @Test
    void move2() {
        Rook r=new Rook("A1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> r.move("A3")
        );
    }


}