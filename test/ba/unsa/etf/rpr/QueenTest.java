package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void mozeLiNaPoziciju() {
        Queen q=new Queen("D1", ChessPiece.Color.WHITE);
        assertTrue(q.mozeLiNaPoziciju("e2"));
    }

    @Test
    void move() {
        Queen q=new Queen("D1", ChessPiece.Color.WHITE);
        assertThrows(
                IllegalArgumentException.class,
                () -> q.move("i1")
        );
    }
}