package ax.ha.tdd.chess.engine.pieces;


import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.pieces.moveBases.DiagonalMovable;
import ax.ha.tdd.chess.engine.pieces.moveBases.DiagonalMoveBase;
import ax.ha.tdd.chess.engine.pieces.moveBases.OrthogonalMovable;
import ax.ha.tdd.chess.engine.pieces.moveBases.OrthogonalMoveBase;


public class Queen extends ChessPieceBase implements ChessPiece {
    private final DiagonalMovable diagonalMovable;
    private final OrthogonalMovable orthogonalMovable;

    public Queen(Color player, Square location) {
        super(PieceType.QUEEN, player, location);
        this.diagonalMovable = new DiagonalMoveBase(PieceType.QUEEN, player, location);
        this.orthogonalMovable = new OrthogonalMoveBase(PieceType.QUEEN, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        return this.diagonalMovable.getAvailableMoves(chessboard).contains(destination) ||
                this.orthogonalMovable.getAvailableMoves(chessboard).contains(destination);
    }
}
