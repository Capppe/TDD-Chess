package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Color;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPieceBase implements ChessPiece{

    public Pawn(Color player, Square location) {
        super(PieceType.PAWN, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        //TODO here goes move logic for pawns
        if(chessboard.getPieceAt(destination) != null){
            return getAvailableTakes(chessboard).contains(destination);
        }
        if(!getAvailableMoves().contains(destination)) { return false; }

        return true;
    }

    private List<Square> getAvailableMoves() {
        List<Square> moves = new ArrayList<>();

        if(isFirstMove()) {
            moves.add(new Square(this.location.getX(), this.location.getY() + (this.color == Color.WHITE ? -2 : +2)));
            moves.add(new Square(this.location.getX(), this.location.getY() + (this.color == Color.WHITE ? -1 : +1)));
        } else if(this.location.getY() > -1 && this.location.getY() < 8){
            moves.add(new Square(this.location.getX(), this.location.getY() + (this.color == Color.WHITE ? -1 : +1)));
        }

        if(canPromote()) {
            //TODO handle promotion
            moves.add(new Square(this.location.getX(), this.location.getY() + (this.color == Color.WHITE ? -1 : +1)));
        }

        return moves;
    }

    private boolean isFirstMove() {
        return switch (this.color) {
            case WHITE -> this.location.getY() == 6;
            case BLACK -> this.location.getY() == 1;
            case null -> false;
        };
    }

    private boolean canPromote() {
        return switch (this.color) {
            case WHITE -> this.location.getY() == 1;
            case BLACK -> this.location.getY() == 6;
            case null -> false;
        };
    }

    private List<Square> getAvailableTakes(Chessboard chessboard) {
        List<Square> takes = new ArrayList<>();
        ChessPiece pieceDiagLeft;
        ChessPiece pieceDiagRight;

        if(this.color == Color.WHITE) {
            pieceDiagLeft = chessboard.getPieceAt(new Square(this.location.getX() - 1, this.location.getY() - 1));
            pieceDiagRight = chessboard.getPieceAt(new Square(this.location.getX() + 1, this.location.getY() - 1));
        } else {
            pieceDiagLeft = chessboard.getPieceAt(new Square(this.location.getX() + 1, this.location.getY() + 1));
            pieceDiagRight = chessboard.getPieceAt(new Square(this.location.getX() - 1, this.location.getY() + 1));
        }

        if(pieceDiagLeft != null && pieceDiagLeft.getColor() != this.color) {
            takes.add(pieceDiagLeft.getLocation());
        }
        if(pieceDiagRight != null && pieceDiagRight.getColor() != this.color) {
            takes.add(pieceDiagRight.getLocation());
        }

        pieceDiagLeft = chessboard.getPieceAt(new Square(this.location.getX()+1, this.location.getY()-1));
        System.out.println("PieceDiagRight: " + pieceDiagRight);
        System.out.println("PieceDiagLeft: " + pieceDiagLeft);
        System.out.println("Available takes: " + takes);
        return takes;
    }
}
