package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Color;

import java.util.List;

public interface ChessPiece {
    /**
     * The graphical symbol to use for display of this piece
     */
    String getSymbol();

    /**
     * Is it a rook, pawn, queen, etc?
     */
    PieceType getType();

    /**
     * White or black?
     */
    Color getColor();

    /**
     * On what square is this piece?
     */
    Square getLocation();

    /**
     * Set the location of the piece on the board
     */
    void setLocation(Square square);

    /**
     * Can the piece move to the destination square on this chessboard?
     */
    boolean canMove(Chessboard chessboard, Square destination);

    /**
     * All available moves
     */
    List<Square> getAvailableMoves(Chessboard chessboard);

    /**
     * Can this piece take the king?
     */
    boolean canTakeKing(Chessboard chessboard);

    /**
     * Can this piece block a check?
     */
    boolean canBlockCheck(Chessboard chessboard, ChessPiece checkingPiece);
}
