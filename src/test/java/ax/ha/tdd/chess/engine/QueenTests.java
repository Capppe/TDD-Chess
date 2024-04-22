package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import ax.ha.tdd.chess.engine.pieces.Queen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenTests {
    @Test
    public void testMoveQueenOrthogonallyLegal() {
        //Arrange
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("a1"));
        chessboard.addPiece(queen);

        //Assert
        assertTrue(queen.canMove(chessboard, new Square("a6")));
        assertTrue(queen.canMove(chessboard, new Square("a7")));
        assertTrue(queen.canMove(chessboard, new Square("a8")));
        assertTrue(queen.canMove(chessboard, new Square("b1")));
        assertTrue(queen.canMove(chessboard, new Square("f1")));
        assertTrue(queen.canMove(chessboard, new Square("h1")));

        //For debugging, you can print the board to console, or if you want
        //to implement a command line interface for the game
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMoveQueenDiagonallyLegal() {
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        chessboard.addPiece(queen);

        String[] moves = {"c5", "b6", "a7", "e5", "f6", "c3", "b2", "e3", "f2", "a1"};
        for(String move : moves) {
            assertTrue(queen.canMove(chessboard, new Square(move)));
        }

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMoveQueenThroughUnits() {
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        Queen queen2 = new Queen(Color.BLACK, new Square("d5"));
        Pawn pawn = new Pawn(Color.BLACK, new Square("g7"));
        Pawn pawn2 = new Pawn(Color.WHITE, new Square("d2"));
        chessboard.addPiece(queen);
        chessboard.addPiece(queen2);
        chessboard.addPiece(pawn);
        chessboard.addPiece(pawn2);

        assertFalse(queen.canMove(chessboard, new Square("d6")));
        assertFalse(queen.canMove(chessboard, new Square("d7")));
        assertFalse(queen.canMove(chessboard, new Square("g8")));
        assertFalse(queen.canMove(chessboard, new Square("d4")));
        assertFalse(queen.canMove(chessboard, new Square("d2")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testTakeWithQueen() {
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        Queen queen2 = new Queen(Color.BLACK, new Square("d8"));
        Pawn pawn = new Pawn(Color.BLACK, new Square("g7"));
        Pawn pawn2 = new Pawn(Color.WHITE, new Square("d1"));
        chessboard.addPiece(queen);
        chessboard.addPiece(queen2);
        chessboard.addPiece(pawn);
        chessboard.addPiece(pawn2);

        assertTrue(queen.canMove(chessboard, new Square("d8")));
        assertTrue(queen.canMove(chessboard, new Square("g7")));
        assertFalse(queen.canMove(chessboard, new Square("d1")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }
}
