package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;

import java.util.*;

public class Knight extends ChessPieceBase implements ChessPiece{

    public Knight(Color player, Square location) {
        super(PieceType.KNIGHT, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        if(!getAvailableMoves(chessboard).contains(destination)) { return false; }
        return true;
    }

    @Override
    public boolean canTakeKing(Chessboard chessboard) {
        return getAvailableMoves(chessboard).contains(chessboard.getKingSquare(this.color == Color.WHITE ? Color.BLACK: Color.WHITE));
    }

    public List<Square> getAvailableMoves(Chessboard chessboard) {
        List<Square> moves = new ArrayList<>();

        int locX = this.location.getX();
        int locY = this.location.getY();
        Map<Integer, int[]> allMoves = getAllMoves();

        for(Map.Entry<Integer, int[]> entry : allMoves.entrySet()) {
            int key = entry.getKey();
            int[] move = entry.getValue();

            for(int m : move) {
                int x = locX + key;
                int y = locY + m;
                if(isOutOfBounds(x, y)) { continue; }

                Square square = new Square(locX + key, locY + m);
                if(chessboard.getPieceAt(square) == null) {
                    moves.add(square);
                } else if(chessboard.getPieceAt(square).getColor() != this.color) {
                    moves.add(square);
                }
            }
        }

        return moves;
    }

    private Map<Integer, int[]> getAllMoves() {
        return new HashMap<>(Map.of(
                -2, new int[]{-1, 1},
                2, new int[]{-1, 1},
                -1, new int[]{-2, 2},
                1, new int[]{-2, 2}
        ));
    }

    private boolean isOutOfBounds(int x, int y) {
        return x > 7 || x < 0 || y > 7 || y < 0;
    }
}
