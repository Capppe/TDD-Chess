package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.pieces.moveBases.DiagonalMoveBase;

public class Bishop extends DiagonalMoveBase implements ChessPiece {
    public Bishop(Color player, Square location) {
        super(PieceType.BISHOP, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        return super.canMove(chessboard, destination);
    }

    @Override
    public void setLocation(Square square) {
        this.location = square;
        super.setLocation(square);
    }

    @Override
    public boolean canTakeKing(Chessboard chessboard) {
        return getAvailableMoves(chessboard).contains(chessboard.getKingSquare(this.color == Color.WHITE ? Color.BLACK: Color.WHITE));
    }
}
