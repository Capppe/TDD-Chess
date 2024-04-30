package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.Bishop;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopTests {

    @Test
    public void testMoveLegally() {
        //Arrange
        Chessboard chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("d4"));
        chessboard.addPiece(bishop);

        //Assert
        String[] moves = {"c5", "b6", "a7", "e5", "f6", "c3", "b2", "e3", "f2", "a1"};
        for(String move : moves) {
            assertTrue(bishop.canMove(chessboard, new Square(move)));
            System.out.println(new ChessboardWriter().print(chessboard));
        }

        //For debugging, you can print the board to console, or if you want
        //to implement a command line interface for the game
    }

    @Test
    public void testMoveOrthogonallyIsIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("d4"));
        chessboard.addPiece(bishop);

        String[] moves = {"d5", "d3", "d2", "e4", "f4", "d4", "c4"};
        for(String move : moves) {
            assertFalse(bishop.canMove(chessboard, new Square(move)));
        }

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testMoveThroughUnitIsIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("d4"));
        Bishop bishop2 = new Bishop(Color.WHITE, new Square("e5"));
        Pawn pawn = new Pawn(Color.BLACK, new Square("c5"));
        chessboard.addPiece(bishop);
        chessboard.addPiece(bishop2);
        chessboard.addPiece(pawn);

        assertFalse(bishop.canMove(chessboard, new Square("f6")));
        assertFalse(bishop.canMove(chessboard, new Square("b6")));
        assertFalse(bishop.canMove(chessboard, new Square("e5")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testTake() {
        Chessboard chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("d4"));
        Bishop bishop2 = new Bishop(Color.BLACK, new Square("e5"));
        Pawn pawn = new Pawn(Color.BLACK, new Square("c5"));
        Pawn pawn2 = new Pawn(Color.BLACK, new Square("c3"));
        chessboard.addPiece(bishop);
        chessboard.addPiece(bishop2);
        chessboard.addPiece(pawn);
        chessboard.addPiece(pawn2);

        assertTrue(bishop.canMove(chessboard, new Square("e5")));
        assertTrue(bishop.canMove(chessboard, new Square("c5")));
        assertTrue(bishop.canMove(chessboard, new Square("c3")));

        System.out.println(new ChessboardWriter().print(chessboard));
    }
}
