package chess;
/**
 *
 * @author Shaan Patel
 * @author Jay Prasad
 *
 */

/**
 * Knight class used to define behaviors of the knight
 */
public class Knight implements Chesspiece {
    /**
     * defines the name of the piece
     */
    String piecename = "chess.Knight";
    /**
     * defines the color of the knight
     */
    ChessColor color;
    /**
     * defines the position of the knight
     */
    ChessPosition position;
    /**
     * defines the checking status of the knight
     */
    Boolean ischecking = false;

    /**
     * constructor that takes in the color of the knight and sets it
     * @param col column
     */
    public Knight(ChessColor col)
    {
        color = col;
    }

    /**
     * gets the position of the knight
     * @return position
     */
    public ChessPosition getPosition(){
        return position;
    }

    /**
     * sets the position of the knight
     * @param pos position
     */
    public void setPosition(ChessPosition pos) {
        position = pos;
    }

    /**
     * gets the color of the knight
     * @return color
     */
    public ChessColor getColor() {
        return color;
    }

    /**
     * gets the name of the knight
     * @return name
     */
    public String getName() {
        return piecename;
    }

    /**
     * sets the checking status of the knight
     * @param val check value
     */
    public void setCheck(boolean val)
    {
        ischecking = val;
    }

    /**
     * gets the checking status of the knight
     * @return check value
     */
    public boolean getCheck()
    {
        return ischecking;
    }

    /**
     * tells whether the move inputted on the knight is a valid move based on its allowed path
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return true if check, else false
     */
    public boolean isValidMove(ChessPosition curPos, ChessPosition endPos) // check theoretical validity of a move for a knight
    {
        if (Math.abs(curPos.getRow() - endPos.getRow()) == 2 && Math.abs(curPos.getCol() - endPos.getCol()) == 1)
            return true;
        if (Math.abs(curPos.getRow() - endPos.getRow()) == 1 && Math.abs(curPos.getCol() - endPos.getCol()) == 2)
            return true;
        return false;
    }

    /**
     * gets the path of the knight, since it can hop over pieces, it is null
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return array of path of piece
     */
    public ChessPosition[] getPath(ChessPosition curPos, ChessPosition endPos) // Knights can hop over pieces, so the path is null
    {
        return null;
    }
}
