package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void mozeLiNaPoziciju() {
        Bishop b=new Bishop("C1", ChessPiece.Color.BLACK);
        assertTrue(b.mozeLiNaPoziciju("a3"));
    }

    @Test
    void move() {
        Bishop b=new Bishop("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> b.move("I2")
        );
    }
}