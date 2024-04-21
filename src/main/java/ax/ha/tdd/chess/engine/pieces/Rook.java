package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPieceBase implements ChessPiece {
    public Rook(Color player, Square location) {
        super(PieceType.ROOK, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        if(!getAvailableMoves(chessboard).contains(destination)) { return false; }
        return true;
    }

    private List<Square> getAvailableMoves(Chessboard chessboard) {
        List<Square> moves = new ArrayList<>();
        int[] deltas = {-1, 1};

        for (int deltaX : deltas) {
            int locX = this.location.getX() + deltaX;
            int locY = this.location.getY();

            while (locX >= 0 && locX < 8) {
                Square square = new Square(locX, locY);

                if (chessboard.getPieceAt(square) == null) {
                    moves.add(square);
                } else {
                    if(chessboard.getPieceAt(square).getColor() != this.color) {
                        moves.add(square);
                    }
                    break;
                }

                locX += deltaX;
            }
        }

        for (int deltaY : deltas) {
            int locX = this.location.getX();
            int locY = this.location.getY() + deltaY;

            while (locY >= 0 && locY < 8) {
                Square square = new Square(locX, locY);

                if (chessboard.getPieceAt(square) == null) {
                    moves.add(square);
                } else {
                    if(chessboard.getPieceAt(square).getColor() != this.color) {
                        moves.add(square);
                    }
                    break;
                }

                locY += deltaY;
            }
        }

        return moves;
    }
}
