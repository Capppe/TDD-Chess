package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.PieceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTests {
    @Test
    public void testMoveOpponentsPieceShouldBeIllegal() {
        //Arrange
        Game game = new GameImpl();
        assertEquals(Color.WHITE, game.getPlayerToMove());

        //Act
        game.move("e7-e6");

        //Assert
        assertEquals(Color.WHITE, game.getPlayerToMove());
        ChessPiece piece = game.getBoard().getPieceAt(new Square("e7"));
        assertEquals(Color.BLACK, piece.getColor());
        assertEquals(PieceType.PAWN, piece.getType());


        //For debugging, you can print the board to console, or if you want
        //to implement a command line interface for the game
        System.out.println(new ChessboardWriter().print(game.getBoard()));
    }

    @Test
    public void testStatusMessages() {
        Game game = new GameImpl();
        assertEquals(Color.WHITE, game.getPlayerToMove());

        game.move("d2-d4");
        assertTrue(game.getLastMoveResult().contains("(Last move)"));

        game.move("e4-e5");
        assertTrue(game.getLastMoveResult().contains("(Piece not found)"));

        game.move("a2-a4");
        assertTrue(game.getLastMoveResult().contains("(Wrong piece)"));

        game.move("b7-b1");
        assertTrue(game.getLastMoveResult().contains("(Invalid move)"));
    }

    @Test
    public void testSwitchesTurn() {
        Game game = new GameImpl();

        assertEquals(Color.WHITE, game.getPlayerToMove());
        game.move("a2-a4");

        assertEquals(Color.BLACK, game.getPlayerToMove());
        game.move("b7-b5");

        assertEquals(Color.WHITE, game.getPlayerToMove());
    }

    @Test
    public void testInvalidMovementString() {
        Game game = new GameImpl();

        game.move("a9-a1");
        assertTrue(game.getLastMoveResult().contains("(Invalid square)"));

        game.move("z9-;1");
        assertTrue(game.getLastMoveResult().contains("(Invalid square)"));
    }
}
