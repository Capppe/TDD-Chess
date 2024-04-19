package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import ax.ha.tdd.chess.engine.pieces.PieceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTests {

    @Test
    public void testMovePawnBackwardsShouldBeIllegal() {
        //Arrange
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        //Assert
        assertFalse(pawn.canMove(chessboard, new Square("e1")));

        //For debugging, you can print the board to console, or if you want
        //to implement a command line interface for the game
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMovePawnForwardsOneStep() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        assertTrue(pawn.canMove(chessboard, new Square("e3")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMovePawnForwardsTwoStepsFirstMove() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        assertTrue(pawn.canMove(chessboard, new Square("e4")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMovePawnForwardsTwoStepsNotFirstMove() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("e3"));
        chessboard.addPiece(pawn);

        assertTrue(pawn.canMove(chessboard, new Square("e4")));
        assertFalse(pawn.canMove(chessboard, new Square("e5")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMovePawnForwardsToOccupiedSquareIsIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("e3"));
        Pawn pawn2 = new Pawn(Color.WHITE, new Square("e4"));
        chessboard.addPiece(pawn);
        chessboard.addPiece(pawn2);

        assertFalse(pawn.canMove(chessboard, new Square("e4")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testTakePieceLegalMove() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("b2"));
        Pawn pawn2 = new Pawn(Color.BLACK, new Square("c3"));
        chessboard.addPiece(pawn);
        chessboard.addPiece(pawn2);

        assertTrue(pawn.canMove(chessboard, new Square("c3")));

        Pawn pawn3 = new Pawn(Color.BLACK, new Square("c6"));
        Pawn pawn4 = new Pawn(Color.WHITE, new Square("d5"));
        chessboard.addPiece(pawn3);
        chessboard.addPiece(pawn4);

        assertTrue(pawn3.canMove(chessboard, new Square("d5")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testTakeOwnPieceIllegalMove() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("b2"));
        Pawn pawn2 = new Pawn(Color.WHITE, new Square("c3"));
        chessboard.addPiece(pawn);
        chessboard.addPiece(pawn2);

        assertFalse(pawn.canMove(chessboard, new Square("c3")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }
}
