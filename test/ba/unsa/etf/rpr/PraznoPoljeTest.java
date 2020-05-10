package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PraznoPoljeTest {

    @Test
    void getPosition() {
        PraznoPolje p=new PraznoPolje("A2", ChessPiece.Color.WHITE);
        assertEquals("A2", p.getPosition());
    }

    @Test
    void getColor() {
        PraznoPolje p=new PraznoPolje("A2", ChessPiece.Color.WHITE);
        assertEquals(ChessPiece.Color.WHITE, p.getColor());
    }

    @Test
    void move() {
        PraznoPolje p=new PraznoPolje("A2", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> p.move("E4")
        );
    }

    @Test
    void mozeLiNaPoziciju() {
        PraznoPolje p=new PraznoPolje("A2", ChessPiece.Color.BLACK);
        assertEquals(true, p.mozeLiNaPoziciju("A3"));
    }
}