package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.Knight;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTests {
    @Test
    public void testMoveLegally() {
        //Arrange
        Chessboard chessboard = new ChessboardImpl();
        Knight knight = new Knight(Color.WHITE, new Square("e5"));
        chessboard.addPiece(knight);

        //Assert
        String[] moves = {"f7", "d7", "g6", "g4", "f3", "d3", "c4", "c6"};
        for(String move : moves) {
            assertTrue(knight.canMove(chessboard, new Square(move)));
        }

        //For debugging, you can print the board to console, or if you want
        //to implement a command line interface for the game
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMoveIllegally() {
        Chessboard chessboard = new ChessboardImpl();
        Knight knight = new Knight(Color.WHITE, new Square("e5"));
        chessboard.addPiece(knight);

        String[] moves = {"f8", "d6", "g7", "g3", "f2", "d4", "a4", "a6"};
        for(String move : moves) {
            assertFalse(knight.canMove(chessboard, new Square(move)));
        }

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testTake() {
        Chessboard chessboard = new ChessboardImpl();
        Knight knight = new Knight(Color.WHITE, new Square("e5"));
        Pawn pawn = new Pawn(Color.BLACK, new Square("f7"));
        Pawn pawn2 = new Pawn(Color.BLACK, new Square("d7"));
        Pawn pawn3 = new Pawn(Color.WHITE, new Square("g4"));
        chessboard.addPiece(knight);
        chessboard.addPiece(pawn);
        chessboard.addPiece(pawn2);
        chessboard.addPiece(pawn3);

        assertTrue(knight.canMove(chessboard, new Square("f7")));
        assertTrue(knight.canMove(chessboard, new Square("d7")));
        assertFalse(knight.canMove(chessboard, new Square("g4")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testJumpOverPiece() {
        Chessboard chessboard = new ChessboardImpl();
        Knight knight = new Knight(Color.WHITE, new Square("b1"));
        Pawn pawn = new Pawn(Color.BLACK, new Square("c2"));
        Pawn pawn2 = new Pawn(Color.WHITE, new Square("a2"));
        Pawn pawn3 = new Pawn(Color.WHITE, new Square("b2"));
        chessboard.addPiece(knight);
        chessboard.addPiece(pawn);
        chessboard.addPiece(pawn2);
        chessboard.addPiece(pawn3);

        assertTrue(knight.canMove(chessboard, new Square("c3")));
        assertTrue(knight.canMove(chessboard, new Square("a3")));
    }
}
