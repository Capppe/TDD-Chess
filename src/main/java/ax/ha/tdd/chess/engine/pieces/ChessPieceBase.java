package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class ChessPieceBase implements ChessPiece {

    protected final Color color;
    protected final PieceType type;

    protected Square location;

    public ChessPieceBase(PieceType type, final Color player,
                          final Square location) {
        this.type = type;
        this.color = player;
        this.location = location;
    }

    public final String getSymbol() {
        return type.getSymbol();
    }

    public PieceType getType() { return type; }

    public Color getColor() {
        return color;
    }

    public Square getLocation() {
        return location;
    }

    public void setLocation(Square square) {
        this.location = square;
    }

    /**
     * Suggestion of design:
     * Checks if the chessPiece can move to a certain destination.
     * I preferred to keep this logic tightly coupled to the pieces instead of the board,
     * since that makes the separation of logic easier.
     *
     * @param chessboard chessboard
     * @param destination destination
     * @return true if piece can move to the destination
     */
    public abstract boolean canMove(final Chessboard chessboard, final Square destination);

    public abstract List<Square> getAvailableMoves(Chessboard chessboard);

    public List<Square> getMovesToSquare(Chessboard chessboard, Square square) {
        List<Square> moves = new ArrayList<>();

        if(!this.canMove(chessboard, square)) { return moves; }

        int locX = this.location.getX();
        int locY = this.location.getY();
        while(locX != square.getX() && locY != square.getY()) {
            
        }

        return moves;
    }

    @Override
    public boolean canBlockCheck(Chessboard chessboard, ChessPiece checkingPiece) {
        List<Square> checkingPieceMoves = checkingPiece.getAvailableMoves(chessboard);
        List<Square> blockingPieceMoves = this.getAvailableMoves(chessboard);

        return blockingPieceMoves.stream().anyMatch(checkingPieceMoves::contains);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPieceBase that = (ChessPieceBase) o;
        return color == that.color && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

    @Override
    public String toString() {
        return getColor().name() + " " + getClass().getSimpleName();
    }
}
