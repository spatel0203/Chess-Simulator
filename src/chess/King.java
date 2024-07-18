package chess;
/**
 *
 * @author Shaan Patel
 * @author Jay Prasad
 *
 */

/**
 * King class that defines behavior of a king
 */
public class King implements Chesspiece{
    /**
     * Defines the name of the piece
     */
    public String piecename = "chess.King";
    /**
     * defines the color of the king
     */
    public ChessColor pieceColor;
    /**
     * defines the position on the board the king is in
     */
    // Maybe make position static
    public ChessPosition position;
    /**
     * states whether the king has moved
     */
    public boolean hasMoved = false;
    /**
     * states if the king is castling
     */
    public Boolean isCastle = false;
    /**
     * states if the king is checking the enemy king
     */
    public Boolean ischecking = false;

    /**
     * constructor that takes in the color of the king and sets it to pieceColor
     * @param col column
     */
    public King(ChessColor col)
    {
        pieceColor = col;
    }

    /**
     * gets the position of the king
     * @return position
     */
    public ChessPosition getPosition() {
        return position;
    }

    /**
     * sets the position of the king
     * @param pos position
     */
    public void setPosition(ChessPosition pos) {
        position = pos;
    }

    /**
     * gets the color of the king
     * @return color
     */
    public ChessColor getColor() {
        return pieceColor;
    }

    /**
     * gets the name of the king
     * @return color
     */
    public String getName() {
        return piecename;
    }

    /**
     * sets the checking status of the king
     * @param val check value
     */
    public void setCheck(boolean val)
    {
        ischecking = val;
    }

    /**
     * gets the checking status of the king
     * @return check status
     */
    public boolean getCheck()
    {
        return ischecking;
    }

    /**
     * states whether the move is valid for the king based under the rule of the king only being able to move one space at a time
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return true if valid move, else false
     */
    public boolean isValidMove(ChessPosition curPos, ChessPosition endPos) // Kings can only move one square
    {
        if (Math.abs(curPos.getRow() - endPos.getRow()) > 1)
            return false;
        if (Math.abs(curPos.getCol() - endPos.getCol()) > 1)
            return false;
        hasMoved=true;
        return true;
    }

    /**
     * Used to tell if the move a king takes is valid or not
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @param board board
     * @param c color
     * @return true if valid move, else false
     */
    public boolean isValidKing(ChessPosition curPos, ChessPosition endPos, Chesspiece[][] board, ChessColor c) {
        if (isValidMove(curPos, endPos))
            return true;
        return false;
    }

    /**
     * used to tell if the king can castle or not
     * @return true if you can castle, else false
     */
    public boolean canCastle(){
        return !hasMoved;
    }

    /**
     * gets the path the king can move on - since the king can only move one square, the path is null
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return array of path
     */
    public ChessPosition[] getPath(ChessPosition curPos, ChessPosition endPos) // since they can only move one square, path is null
    {
        return null;
    }
}
