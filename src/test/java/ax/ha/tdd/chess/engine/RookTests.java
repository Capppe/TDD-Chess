package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import ax.ha.tdd.chess.engine.pieces.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTests {

    @Test
    public void testMoveRookVerticallyLegal() {
        //Arrange
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("a1"));
        chessboard.addPiece(rook);

        //Assert
        assertTrue(rook.canMove(chessboard, new Square("a6")));
        assertTrue(rook.canMove(chessboard, new Square("a7")));
        assertTrue(rook.canMove(chessboard, new Square("a8")));

        //For debugging, you can print the board to console, or if you want
        //to implement a command line interface for the game
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMoveRookHorizontallyLegal() {
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("a1"));
        chessboard.addPiece(rook);

        assertTrue(rook.canMove(chessboard, new Square("f1")));
        assertTrue(rook.canMove(chessboard, new Square("g1")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMoveRookDiagonallyIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("a1"));
        chessboard.addPiece(rook);

        assertFalse(rook.canMove(chessboard, new Square("h8")));
        assertFalse(rook.canMove(chessboard, new Square("g2")));
        assertFalse(rook.canMove(chessboard, new Square("b4")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMoveRookThroughUnits() {
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("a1"));
        Rook rookB = new Rook(Color.BLACK, new Square("c1"));
        Pawn pawn = new Pawn(Color.WHITE, new Square("a5"));
        chessboard.addPiece(rook);
        chessboard.addPiece(rookB);
        chessboard.addPiece(pawn);

        assertFalse(rook.canMove(chessboard, new Square("a6")));
        assertFalse(rook.canMove(chessboard, new Square("a8")));
        assertFalse(rook.canMove(chessboard, new Square("d1")));
        assertFalse(rook.canMove(chessboard, new Square("g1")));

        //Assert can take the black, but not white
        assertTrue(rook.canMove(chessboard, new Square("c1")));
        assertFalse(rook.canMove(chessboard, new Square("a5")));


        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testTakeWithRook() {
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("a1"));
        Rook rookB = new Rook(Color.BLACK, new Square("c1"));
        Pawn pawn = new Pawn(Color.BLACK, new Square("a5"));
        chessboard.addPiece(rook);
        chessboard.addPiece(rookB);
        chessboard.addPiece(pawn);

        assertTrue(rook.canMove(chessboard, new Square("a5")));
        assertTrue(rook.canMove(chessboard, new Square("c1")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }
}
