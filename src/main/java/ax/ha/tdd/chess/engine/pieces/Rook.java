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
        //if(!getAvailableMoves(chessboard).contains(destination)) { return false; }
        //return true;
        return super.canMove(chessboard, destination);
    }
}
