package ax.ha.tdd.chess.engine.pieces.moveBases;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.pieces.ChessPieceBase;
import ax.ha.tdd.chess.engine.pieces.PieceType;

import java.util.ArrayList;
import java.util.List;

public class DiagonalMoveBase extends ChessPieceBase implements DiagonalMovable {
    public DiagonalMoveBase(PieceType type, Color player, Square location) {
        super(type, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        System.out.println("MOVES: " + getAvailableMoves(chessboard));
        return getAvailableMoves(chessboard).contains(destination);
    }

    @Override
    public boolean canTakeKing(Chessboard chessboard) {
        return this.getAvailableMoves(chessboard).contains(chessboard.getKingSquare(this.color == Color.WHITE ? Color.WHITE : Color.BLACK));
    }

    @Override
    public List<Square> getAvailableMoves(Chessboard chessboard) {
        List<Square> moves = new ArrayList<>();

        for(int dx = -1; dx <= 1; dx += 2) {
            for(int dy = -1; dy <= 1; dy += 2) {
                int i = 1;
                int locX = this.location.getX();
                int locY = this.location.getY();
                while (true) {
                    locX = this.location.getX() + i * dx;
                    locY = this.location.getY() + i * dy;
                    if(locX > 7 || locX < 0 || locY > 7 || locY < 0) { break; }

                    Square square = new Square(locX, locY);
                    if (chessboard.getPieceAt(square) == null) {
                        moves.add(square);
                    } else {
                        if (chessboard.getPieceAt(square).getColor() != this.color) {
                            moves.add(square);
                        }
                        break;
                    }
                    i++;
                }
            }
        }

        return moves;
    }
}
