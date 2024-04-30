package ax.ha.tdd.chess.engine.pieces.moveBases;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;

import java.util.List;

public interface DiagonalMovable {
    List<Square> getAvailableMoves(Chessboard chessboard);
    void setLocation(Square square);
}
