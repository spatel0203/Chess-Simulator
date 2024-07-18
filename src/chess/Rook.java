package chess;
/**
 *
 * @author Shaan Patel
 * @author Jay Prasad
 *
 */

/**
 * Rook class that defines the behavior of the rook
 */
public class Rook implements Chesspiece{
    /**
     * defines the name of the rook
     */
    String piecename = "chess.Rook";
    /**
     * defines the color of the rook
     */
    ChessColor color;
    /**
     * defines the position of the rook
     */
    ChessPosition position;
    /**
     * defines the checking status of the rook
     */
    Boolean ischecking = false;
    /**
     * defines whether the rook has moved yet
     */
    boolean hasMoved = false;

    /**
     * constructor that takes in the color of the rook and sets it to the rook
     * @param col color
     */
    public Rook(ChessColor col)
    {
        color = col;
    }

    /**
     * gets the position of the rook
     * @return position
     */
    public ChessPosition getPosition() {
        return position;
    }

    /**
     * sets the position of the rook
     * @param pos position
     */
    public void setPosition(ChessPosition pos) {
        position = pos;
    }

    /**
     * gets the color of the rook
     * @return color
     */
    public ChessColor getColor() {
        return color;
    }

    /**
     * gets the name of the rook
     * @return name
     */
    public String getName() {
        return piecename;
    }

    /**
     * sets the checking status of the rook
     * @param val check value
     */
    public void setCheck(boolean val)
    {
        ischecking = val;
    }

    /**
     * gets the checking status of the rook
     * @return check value
     */
    public boolean getCheck()
    {
        return ischecking;
    }

    /**
     * tells whether the move of the rook is valid based on its movement behavior
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return true if valid move, else false
     */
    public boolean isValidMove(ChessPosition curPos, ChessPosition endPos) // checks theoretical validity of a move for a rook
    {
        if (Math.abs(curPos.getRow() - endPos.getRow()) >= 1 && (Math.abs(curPos.getCol() - endPos.getCol()) == 0)){
            return true;
        }
        if (Math.abs(curPos.getRow() - endPos.getRow()) == 0 && Math.abs(curPos.getCol() - endPos.getCol()) >= 1){
            return true;
        }
        return false;
    }

    /**
     * tells whether the rook can castle or not
     * @return true if you can castle, else false
     */
    public boolean canCastle(){
        return !hasMoved;
    }

    /**
     * gets the path of the rook
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return array of path
     */
    public ChessPosition[] getPath(ChessPosition curPos, ChessPosition endPos) // returns path for a rook
    {
        ChessPosition[] path = new ChessPosition[8];
        if (curPos.getRow() - endPos.getRow() == 0)
        {
            for (int i = 1; i < Math.abs(curPos.getCol() - endPos.getCol()); i++)
            {
                if (curPos.getCol() > endPos.getCol())
                {
                    ChessPosition temp = new ChessPosition(curPos.getRow(), curPos.getCol() - i);
                    path[i] = temp;
                }
                else {
                    ChessPosition temp = new ChessPosition(curPos.getRow(), curPos.getCol() + i);
                    path[i] = temp;
                }
            }
        }
        if (curPos.getCol() - endPos.getCol() == 0)
        {
            for (int i = 1; i < Math.abs(curPos.getRow() - endPos.getRow()); i++)
            {
                if (curPos.getRow() > endPos.getRow())
                {
                    ChessPosition temp = new ChessPosition(curPos.getRow() - i, curPos.getCol());
                    path[i] = temp;
                }
                else {
                    ChessPosition temp = new ChessPosition(curPos.getRow() + i, curPos.getCol());
                    path[i] = temp;
                }
            }
        }
        return path;
    }
}