package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Color;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPieceBase implements ChessPiece {

    public Pawn(Color player, Square location) {
        super(PieceType.PAWN, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        if(chessboard.getPieceAt(destination) != null){
            return getAvailableTakes(chessboard).contains(destination);
        }
        return getAvailableMoves(chessboard).contains(destination);
    }

    @Override
    public boolean canTakeKing(Chessboard chessboard) {
        return getAvailableTakes(chessboard).contains(chessboard.getKingSquare(this.color == Color.WHITE ? Color.BLACK: Color.WHITE));
    }

    public List<Square> getAvailableMoves(Chessboard chessboard) {
        List<Square> moves = new ArrayList<>();
        int posX = this.location.getX();
        int posY = this.location.getY();

        if(isFirstMove()) {
            moves.add(new Square(posX, posY + (this.color == Color.WHITE ? -2 : +2)));
            moves.add(new Square(posX, posY + (this.color == Color.WHITE ? -1 : +1)));
        } else if(posY > 0 && posY < 7){
            moves.add(new Square(posX, posY + (this.color == Color.WHITE ? -1 : +1)));
        }

        if(canPromote()) {
            //TODO handle promotion
            moves.add(new Square(posX, posY + (this.color == Color.WHITE ? -1 : +1)));
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
        ChessPiece pieceDiagLeft = null;
        ChessPiece pieceDiagRight = null;
        int posX = this.location.getX();
        int posY = this.location.getY();

        if (this.color == Color.WHITE) {
            if(posX - 1 > -1 && posY - 1 > -1) { pieceDiagLeft = chessboard.getPieceAt(new Square(posX - 1, posY - 1)); }
            if(posX + 1 < 8 && posY - 1 > -1) { pieceDiagRight = chessboard.getPieceAt(new Square(posX + 1, posY - 1)); }
        } else {
            if(posX + 1 < 8 && posY + 1 < 8) { pieceDiagLeft = chessboard.getPieceAt(new Square(posX + 1, posY + 1)); }
            if(posX - 1 > -1 && posY + 1 < 8) { pieceDiagRight = chessboard.getPieceAt(new Square(posX - 1, posY + 1)); }
        }

        if(pieceDiagLeft != null && pieceDiagLeft.getColor() != this.color) {
            takes.add(pieceDiagLeft.getLocation());
        }
        if(pieceDiagRight != null && pieceDiagRight.getColor() != this.color) {
            takes.add(pieceDiagRight.getLocation());
        }
        return takes;
    }
}
