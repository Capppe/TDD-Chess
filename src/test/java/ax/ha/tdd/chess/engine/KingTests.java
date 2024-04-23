package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.King;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import ax.ha.tdd.chess.engine.pieces.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingTests {
    @Test
    public void testMoveKingLegally() {
        //Arrange
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("d4"));
        chessboard.addPiece(king);

        //Assert
        assertTrue(king.canMove(chessboard, new Square("d5")));
        assertTrue(king.canMove(chessboard, new Square("e5")));
        assertTrue(king.canMove(chessboard, new Square("e4")));
        assertTrue(king.canMove(chessboard, new Square("e3")));
        assertTrue(king.canMove(chessboard, new Square("d3")));
        assertTrue(king.canMove(chessboard, new Square("c3")));
        assertTrue(king.canMove(chessboard, new Square("c4")));
        assertTrue(king.canMove(chessboard, new Square("c5")));

        //For debugging, you can print the board to console, or if you want
        //to implement a command line interface for the game
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMoveKingIllegally() {
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("d4"));
        chessboard.addPiece(king);

        assertFalse(king.canMove(chessboard, new Square("d6")));
        assertFalse(king.canMove(chessboard, new Square("e6")));
        assertFalse(king.canMove(chessboard, new Square("c6")));
        assertFalse(king.canMove(chessboard, new Square("f3")));
        assertFalse(king.canMove(chessboard, new Square("d6")));
        assertFalse(king.canMove(chessboard, new Square("c2")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMoveKingThroughUnits() {
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("d4"));
        Pawn pawn = new Pawn(Color.WHITE, new Square("d3"));
        Pawn pawn2 = new Pawn(Color.WHITE, new Square("d5"));
        Pawn pawn3 = new Pawn(Color.WHITE, new Square("c4"));
        chessboard.addPiece(king);
        chessboard.addPiece(pawn);
        chessboard.addPiece(pawn2);
        chessboard.addPiece(pawn3);

        assertFalse(king.canMove(chessboard, new Square("d3")));
        assertFalse(king.canMove(chessboard, new Square("d5")));
        assertFalse(king.canMove(chessboard, new Square("c4")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testTakeWithKing() {
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("d4"));
        Pawn pawn = new Pawn(Color.BLACK, new Square("d3"));
        Pawn pawn2 = new Pawn(Color.BLACK, new Square("d5"));
        Pawn pawn3 = new Pawn(Color.BLACK, new Square("c4"));
        chessboard.addPiece(king);
        chessboard.addPiece(pawn);
        chessboard.addPiece(pawn2);
        chessboard.addPiece(pawn3);

        assertTrue(king.canMove(chessboard, new Square("d3")));
        assertTrue(king.canMove(chessboard, new Square("d5")));
        assertTrue(king.canMove(chessboard, new Square("c4")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    //@Test TODO implement
    public void testMoveKingToChess() {
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("d4"));
        Rook rook = new Rook(Color.BLACK, new Square("c1"));

        chessboard.addPiece(king);
        chessboard.addPiece(rook);

        assertFalse(king.canMove(chessboard, new Square("c4")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }
}
