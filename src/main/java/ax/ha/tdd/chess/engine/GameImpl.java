package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.King;
import ax.ha.tdd.chess.engine.pieces.PieceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GameImpl implements Game{

    final ChessboardImpl board = ChessboardImpl.startingBoard();

    boolean isNewGame = true;
    Color playerToMove = Color.WHITE;
    String statusMessage = "";

    //Check helpers
    List<ChessPiece> blockers = new ArrayList<>();
    boolean isCheck = false;

    @Override
    public Color getPlayerToMove() {
        //TODO this should reflect the current state.
        playerToMove = playerToMove == Color.WHITE ? Color.WHITE : Color.BLACK;
        return playerToMove;
    }

    @Override
    public Chessboard getBoard() {
        return board;
    }

    @Override
    public String getLastMoveResult() {
        //TODO this should be used to show the player what happened
        //Illegal move, correct move, e2 moved to e4 etc. up to you!
        if (isNewGame) {
            return "Game hasn't begun";
        }
        return statusMessage;
    }

    @Override
    public void move(String move) {
        System.out.println("Player tried to perform move: " + move);
        isNewGame = false;
        //TODO this should trigger your move logic.
        //1. Parse the source and destination of the input "move"
        Square[] squares = parseMove(move);
        if(squares == null) { return; }
        Square source = squares[0];
        Square dest = squares[1];
        ChessPiece piece = board.getPieceAt(source);

        //2. Check if the piece is allowed to move to the destination
        if(piece == null) {
            this.statusMessage = "(Piece not found) No piece at " + source.toAlgebraic();
            return;
        }
        if(this.getPlayerToMove() != piece.getColor()) {
            this.statusMessage = "(Wrong piece) " + piece + "(" + source.toAlgebraic() + ") is not your piece";
            return;
        }
        if(isCheck && !blockers.contains(piece)) {
            this.statusMessage = "(Invalid move) King is under attack";
            return;
        }
        if(!piece.canMove(board, dest)) {
            this.statusMessage = "(Invalid move) " + piece + "(" + source.toAlgebraic() + ") -> " + dest.toAlgebraic();
            return;
        }

        //3. If so, update board (and last move message), otherwise only update last move message to show that an illegal move was tried
        board.movePiece(source, dest);
        if(piece.canTakeKing(board)) {
            King king = (King) board.getPieceAt(board.getKingSquare(piece.getColor() == Color.WHITE ? Color.WHITE : Color.BLACK));
            isCheck = true;
            blockers = new ArrayList<>();
            for(ChessPiece[] pieces : board) {
                for(ChessPiece blockingPiece : pieces) {
                    if(blockingPiece == null) { continue; }
                    if (blockingPiece.canBlockCheck(board, piece) && blockingPiece.getType() != PieceType.KING) {
                        blockers.add(blockingPiece);
                    }
                }
            }

            if(king.getAvailableMoves(board).isEmpty()) {
                if(blockers.isEmpty()) {
                    //TODO checkmate
                    System.out.println("Checkmate?");
                }
            }
        }
        this.switchPlayerToMove();
        this.statusMessage = "(Last move) " + piece + "(" + source.toAlgebraic() + " -> " + dest.toAlgebraic() + ")";
    }

    private Square[] parseMove(String move) {
        String[] pos = move.split("-");
        if(!pos[0].matches("[a-h][1-8]")) { this.statusMessage = "(Invalid square) the square " + pos[0] + " is invalid!"; return null; }
        if(!pos[1].matches("[a-h][1-8]")) { this.statusMessage = "(Invalid square) the square " + pos[1] + " is invalid!"; return null; }
        Square sqr1 = new Square(pos[0]);
        Square sqr2 = new Square(pos[1]);
        return new Square[]{sqr1, sqr2};
    }

    private void switchPlayerToMove() {
        if (Objects.requireNonNull(playerToMove) == Color.WHITE) {
            playerToMove = Color.BLACK;
        } else {
            playerToMove = Color.WHITE;
        }
    }
}
