package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.pieces.moveBases.OrthogonalMoveBase;

public class Rook extends OrthogonalMoveBase implements ChessPiece {
    public Rook(Color player, Square location) {
        super(PieceType.ROOK, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        return super.canMove(chessboard, destination);
    }

    @Override
    public boolean canTakeKing(Chessboard chessboard) {
        return this.getAvailableMoves(chessboard).contains(chessboard.getKingSquare(this.color == Color.WHITE ? Color.BLACK: Color.WHITE));
    }
}
