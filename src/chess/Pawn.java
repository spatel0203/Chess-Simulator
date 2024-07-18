package chess;
/**
 *
 * @author Shaan Patel
 * @author Jay Prasad
 *
 */

/**
 * Pawn class that defines the behavior of a pawn
 */
public class Pawn implements Chesspiece {
    /**
     * defines the piece name
     */
    String piecename = "chess.Pawn";
    /**
     * defines the color of the pawn
     */
    ChessColor color;
    /**
     * defines the position of the pawn
     */
    ChessPosition position;
    /**
     * defines whether the pawn has already moved
     */
    boolean hasmoved = false;
    /**
     * defines if the pawn has moved two spaces or not
     */
    boolean movedtwo = false;
    /**
     * defines if the pawn is checking the king or not
     */
    boolean ischecking = false;
    /**
     * defines whether the pawn is promoted or not
     */
    boolean promoted = false;

    /**
     * constructor that takes in the color of the piece and sets it to the pawn
     * @param col column
     */
    public Pawn(ChessColor col)
    {
        color = col;
    }

    /**
     * gets the position of the pawn
     * @return position
     */
    public ChessPosition getPosition() {
        return position;
    }

    /**
     * sets the position of the pawn
     * @param pos position
     */
    public void setPosition(ChessPosition pos) {
        position = pos;
    }

    /**
     * gets the color of the pawn
     * @return color
     */
    public ChessColor getColor() {
        return color;
    }

    /**
     * gets the name of the pawn
     * @return name
     */
    public String getName() {
        return piecename;
    }

    /**
     * sets the hasMoved field for the pawn
     * @param val hasMoved value
     */
    public void setHasMoved(boolean val) {
        hasmoved = val;
    }

    /**
     * sets the checking status of the pawn
     * @param val check value
     */
//    public void setPromoted(boolean promoted) {this.promoted = promoted;}
    public void setCheck(boolean val)
    {
        ischecking = val;
    }

    /**
     * gets the checking status of the pawn
     * @return check value
     */
    public boolean getCheck()
    {
        return ischecking;
    }

    /**
     * tells whether the move is valid or not based on the pawns behavior
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return true if valid move, else false
     */
    public boolean isValidMove(ChessPosition curPos, ChessPosition endPos) // checks if it would theoretically be possible to move to the ending position
    {
        if (!hasmoved)
        {
            // Hey
            if (Math.abs(curPos.getRow() - endPos.getRow()) == 2 && (curPos.getCol() == endPos.getCol()))
            {
                this.setHasMoved(true);
                movedtwo = true;
                return true;
            }
        }
        if (Math.abs(curPos.getRow() - endPos.getRow()) != 1)
        {
            return false;
        }
        if (Math.abs(curPos.getCol() - endPos.getCol()) > 1)
        {
            return false;
        }
        this.setHasMoved(true);
        return true;
    }

    /**
     * tells whether the pawn is valid in its movement based on capturing another pawn and moving forwards.
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @param endingPiece end piece
     * @param color color
     * @param board board
     * @param prev previous piece
     * @return true if pawn is valid, else false
     */
    public boolean isValidPawn(ChessPosition curPos, ChessPosition endPos, Chesspiece endingPiece, ChessColor color, Chesspiece[][] board, Chesspiece prev) // Rules only specific to pawns
    {
//        if (!isValidMove(curPos, endPos))
//            return false;
        if (Math.abs(curPos.getCol() - endPos.getCol()) == 1) // Pawns can only change columns if they are capturing a piece
        {
            if (endingPiece == null) {
                if (color == ChessColor.WHITE) {
                    if (endPos.getRow() > 0) {
                        Chesspiece piece = board[endPos.getRow() - 1][endPos.getCol()];
                        if (piece instanceof Pawn) {
                            if (piece == prev && ((Pawn) prev).movedtwo) {
                                board[endPos.getRow() - 1][endPos.getCol()] = null;
                                return true;
                            }
                        }
                    }
                }
                else {
                    if (endPos.getRow() < 7) {
                        Chesspiece piece = board[endPos.getRow() + 1][endPos.getCol()];
                        if (piece instanceof Pawn) {
                            if (piece == prev && ((Pawn) prev).movedtwo) {
                                board[endPos.getRow() + 1][endPos.getCol()] = null;
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        }
        if (Math.abs(curPos.getCol() - endPos.getCol()) == 0) // Pawns cannot move forward if there is a piece in front of them (i.e. they cannot capture moving forward)
        {
            //
            if (endingPiece != null || board[endPos.getRow()][endPos.getCol()] != null)
                return false;
        }
        if (color == ChessColor.WHITE) // Pawns can only move forward
        {
            if (!(endPos.getRow() - curPos.getRow() > 0))
                return false;
        }
        if (color == ChessColor.BLACK)
        {
            if (!(curPos.getRow() - endPos.getRow() > 0)) // Pawns can only move forward
                return false;
        }
        // Might be endPos.getRow() == 0
        if (color == ChessColor.BLACK && endPos.getRow() == 0) {
            this.promoted = true;
            // Pawn Promotion
        }
        if (color == ChessColor.WHITE && endPos.getRow() == 7) {
            this.promoted = true;
        }
        return true;
    }

    /**
     * gets the path of the pawn, since it can only move one space at a time it is null
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return array of path
     */
    public ChessPosition[] getPath(ChessPosition curPos, ChessPosition endPos) // Pawns can only move one space at a time --> there path is null.
    {
        return null;
    }
}