package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPieceBase implements ChessPiece {
    public King(Color player, Square location) {
        super(PieceType.KING, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        return getAvailableMoves(chessboard).contains(destination);
    }

    @Override
    public boolean canTakeKing(Chessboard chessboard) {
        //return getAvailableMoves(chessboard).contains(chessboard.getKingSquare(this.color == Color.WHITE ? Color.WHITE : Color.BLACK));
        return false;
    }

    public List<Square> getAvailableMoves(Chessboard chessboard) {
        List<Square> moves = new ArrayList<>();
        int[][] directions = {
                {1, 0},
                {1, 1},
                {0, 1},
                {-1, 1},
                {-1, 0},
                {-1, -1},
                {0, -1},
                {1, -1}
        };

        for (int[] direction : directions) {
            int deltaX = this.location.getX() + direction[0];
            int deltaY = this.location.getY() + direction[1];

            if (isOutOfBounds(deltaX, deltaY)) {
                continue;
            }

            Square square = new Square(deltaX, deltaY);
            if (chessboard.getPieceAt(square) == null) {
                moves.add(square);
            } else if (chessboard.getPieceAt(square).getColor() != this.color) {
                moves.add(square);
            }
        }

        return moves;
    }

    private boolean isOutOfBounds(int x, int y) {
        return x > 7 || x < 0 || y > 7 || y < 0;
    }

    private boolean canCastle() { //TODO implement
        return false;
    }
}
