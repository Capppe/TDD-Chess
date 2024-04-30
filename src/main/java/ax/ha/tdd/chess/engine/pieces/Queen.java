package ax.ha.tdd.chess.engine.pieces;


import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.pieces.moveBases.DiagonalMovable;
import ax.ha.tdd.chess.engine.pieces.moveBases.DiagonalMoveBase;
import ax.ha.tdd.chess.engine.pieces.moveBases.OrthogonalMovable;
import ax.ha.tdd.chess.engine.pieces.moveBases.OrthogonalMoveBase;

import java.util.ArrayList;
import java.util.List;


public class Queen extends ChessPieceBase implements ChessPiece {
    private DiagonalMovable diagonalMovable;
    private OrthogonalMovable orthogonalMovable;

    public Queen(Color player, Square location) {
        super(PieceType.QUEEN, player, location);
        this.diagonalMovable = new DiagonalMoveBase(PieceType.QUEEN, player, location);
        this.orthogonalMovable = new OrthogonalMoveBase(PieceType.QUEEN, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        System.out.println("Legal diag moves: " + this.diagonalMovable.getAvailableMoves(chessboard));
        System.out.println("Legal orto moves: " + this.orthogonalMovable.getAvailableMoves(chessboard));
        System.out.println("Location: " + this.location.getX() + " - " + this.location.getY());
        if(this.diagonalMovable.getAvailableMoves(chessboard).contains(destination)) {
            return true;
        }
        return this.orthogonalMovable.getAvailableMoves(chessboard).contains(destination);
    }

    @Override
    public List<Square> getAvailableMoves(Chessboard chessboard) {
        List<Square> moves = new ArrayList<>();
        moves.addAll(this.diagonalMovable.getAvailableMoves(chessboard));
        moves.addAll(this.orthogonalMovable.getAvailableMoves(chessboard));

        return moves;
    }

    @Override
    public boolean canTakeKing(Chessboard chessboard) {
        System.out.println("Here");
        System.out.println(this.orthogonalMovable.getAvailableMoves(chessboard));
        System.out.println(this.diagonalMovable.getAvailableMoves(chessboard));
        System.out.println("King: " + chessboard.getKingSquare(this.color == Color.WHITE ? Color.BLACK: Color.WHITE));

        return this.orthogonalMovable.getAvailableMoves(chessboard).contains(chessboard.getKingSquare(this.color == Color.WHITE ? Color.BLACK: Color.WHITE))
                || this.diagonalMovable.getAvailableMoves(chessboard).contains(chessboard.getKingSquare(this.color == Color.WHITE ? Color.BLACK: Color.WHITE));
    }

    /*
    public boolean canBlockCheck(Chessboard chessboard, ChessPiece checkingPiece, King king) {
        List<Square> checkingPieceMoves = checkingPiece.getAvailableMoves(chessboard);
        List<Square> blockingPieceMoves = this.getAvailableMoves(chessboard);

        if(blockingPieceMoves.stream().anyMatch(checkingPieceMoves::contains)) {
            return true;
        }

        return false;
    }

     */

    @Override
    public void setLocation(Square square) {
        this.location = square;
        this.diagonalMovable.setLocation(square);
        this.orthogonalMovable.setLocation(square);
    }
}
