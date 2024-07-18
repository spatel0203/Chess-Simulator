package chess;

/**
 *
 * @author Shaan Patel
 * @author Jay Prasad
 *
 */

/**
 * A 2-D 8x8 Array that holds the chesspieces on the board and defines the logic for what moves are legal and illegal
 */
public class Chessboard {
    /**
     * 2-D Array that holds each game piece
     */
    Chesspiece[][] gameboard;
    /**
     * Array that holds each black piece
     */
    Chesspiece[] blackpieces;
    /**
     * Array that holds each white piece
     */
    Chesspiece[] whitepieces;
    /**
     * Booleans that tell whether a checkmate exists or not
     */
    boolean blackcheckmate = false, whitecheckmate = false;
    /**
     * Boolean to represent pawnPromoting
     */
    private boolean pawnPromote = false;
    /**
     * Holds the previousMove of the piece
     */
    private Chesspiece previousMove;

    /**
     * Constructor that initializes the gameboard to be an 8x8 and 16 pieces for each color
     */
    public Chessboard()
    {
        this.gameboard = new Chesspiece[8][8];
        this.blackpieces = new Chesspiece[16];
        this.whitepieces = new Chesspiece[16];
        this.initializeBoard();
    }

    /**
     * returns what needs to be promoted
     *
     * @return void
     */
    public boolean getPawnPromote() {
        return this.pawnPromote;
    }

    /**
     * sets the pawn that's being promoted
     * @param pawnPromote whether pawn is promoted or not
     */
    public void setPawnPromote(boolean pawnPromote) {
        this.pawnPromote = pawnPromote;
    }

    /**
     * initializes the board by putting the pieces all in the correct spots and setting each piece up
     */
    public void initializeBoard()
    {
        // Initialize board with starting position
        // add pieces to list of black and white pieces
        // set the position for all of the pieces
        for (int j = 0; j < 8; j++)
        {
            gameboard[1][j] = new Pawn(ChessColor.WHITE);
            whitepieces[j] = gameboard[1][j];
            ChessPosition pawntemp = new ChessPosition(1, j);
            whitepieces[j].setPosition(pawntemp);

            gameboard[6][j] = new Pawn(ChessColor.BLACK);
            blackpieces[j] = gameboard[6][j];
            ChessPosition Bpawntemp = new ChessPosition(6, j);
            blackpieces[j].setPosition(Bpawntemp);
        }
        //White chess.Rook & Black chess.Rook
        gameboard[0][0] = new Rook(ChessColor.WHITE);
        whitepieces[8] = gameboard[0][0];
        ChessPosition WR = new ChessPosition(0, 0);
        whitepieces[8].setPosition(WR);

        gameboard[0][7] = new Rook(ChessColor.WHITE);
        whitepieces[9] = gameboard[0][7];
        ChessPosition WR2 = new ChessPosition(0, 7);
        whitepieces[9].setPosition(WR2);

        gameboard[7][0] = new Rook(ChessColor.BLACK);
        blackpieces[8] = gameboard[7][0];
        ChessPosition BR = new ChessPosition(7, 0);
        blackpieces[8].setPosition(BR);

        gameboard[7][7] = new Rook(ChessColor.BLACK);
        blackpieces[9] = gameboard[7][7];
        ChessPosition BR2 = new ChessPosition(7, 7);
        blackpieces[9].setPosition(BR2);
        //White and Black Knights
        gameboard[0][6] = new Knight(ChessColor.WHITE);
        whitepieces[10] = gameboard[0][6];
        ChessPosition WN = new ChessPosition(0, 6);
        whitepieces[10].setPosition(WN);

        gameboard[0][1] = new Knight(ChessColor.WHITE);
        whitepieces[11] = gameboard[0][1];
        ChessPosition WN2 = new ChessPosition(0, 1);
        whitepieces[11].setPosition(WN2);

        gameboard[7][6] = new Knight(ChessColor.BLACK);
        blackpieces[10] = gameboard[7][6];
        ChessPosition BN = new ChessPosition(7, 6);
        blackpieces[10].setPosition(BN);

        gameboard[7][1] = new Knight(ChessColor.BLACK);
        blackpieces[11] = gameboard[7][1];
        ChessPosition BN2 = new ChessPosition(7, 1);
        blackpieces[11].setPosition(BN2);
        //White and Black Bishops
        gameboard[0][2] = new Bishop(ChessColor.WHITE);
        whitepieces[12] = gameboard[0][2];
        ChessPosition WB = new ChessPosition(0, 2);
        whitepieces[12].setPosition(WB);

        gameboard[0][5] = new Bishop(ChessColor.WHITE);
        whitepieces[13] = gameboard[0][5];
        ChessPosition WB2 = new ChessPosition(0, 5);
        whitepieces[13].setPosition(WB2);

        gameboard[7][2] = new Bishop(ChessColor.BLACK);
        blackpieces[12] = gameboard[7][2];
        ChessPosition BB = new ChessPosition(7, 2);
        blackpieces[12].setPosition(BB);

        gameboard[7][5] = new Bishop(ChessColor.BLACK);
        blackpieces[13] = gameboard[7][5];
        ChessPosition BB2 = new ChessPosition(7, 5);
        blackpieces[13].setPosition(BB2);
        //White and Black Queens
        gameboard[0][4] = new Queen(ChessColor.WHITE);
        whitepieces[14] = gameboard[0][4];
        ChessPosition WQ = new ChessPosition(0, 4);
        whitepieces[14].setPosition(WQ);

        gameboard[7][4] = new Queen(ChessColor.BLACK);
        blackpieces[14] = gameboard[7][4];
        ChessPosition BQ = new ChessPosition(7, 4);
        blackpieces[14].setPosition(BQ);
        //White and Black Kings
        gameboard[0][3] = new King(ChessColor.WHITE);
        whitepieces[15] = gameboard[0][3];
        ChessPosition WK = new ChessPosition(0, 3);
        whitepieces[15].setPosition(WK);

        gameboard[7][3] = new King(ChessColor.BLACK);
        blackpieces[15] = gameboard[7][3];
        ChessPosition BK = new ChessPosition(7, 3);
        blackpieces[15].setPosition(BK);
    }

    /**
     * Takes in the current position of a piece,the position that it is being moved to and the end chesspiece in order to move the piece under correct chess rules
     * @param curPos current position of piece
     * @param toPos end position of piece
     * @param end chess piece on end position
     * @return true if move was successful
     */

    public boolean movePiece(ChessPosition curPos, ChessPosition toPos, Chesspiece end)
    {
        Chesspiece piece = gameboard[curPos.getRow()][curPos.getCol()];
        Chesspiece tempPiece = gameboard[toPos.getRow()][toPos.getCol()];
        // if the user selects a space on the gameboard without a piece, return false
        if(piece == null)
            return false;
        //Stop Wrong Castling
        if(piece instanceof King && tempPiece instanceof Rook && (piece.getColor()==tempPiece.getColor())) {
            return false;
        }
        //Check for short castle
        boolean isCastle = false;
        if(piece instanceof King && curPos.getCol()-toPos.getCol()==2 && ((King)piece).canCastle()){
            toPos.setCol(toPos.getCol()-1);
            tempPiece = gameboard[toPos.getRow()][toPos.getCol()];
            isCastle = true;
        }
        //Check for long castle
        if(piece instanceof King && curPos.getCol()-toPos.getCol()==-2 && ((King)piece).canCastle()){
            toPos.setCol(toPos.getCol()+2);
            tempPiece = gameboard[toPos.getRow()][toPos.getCol()];
            isCastle = true;
        }
        // if the user selects a space on the gameboard with the same color as the beginning piece, return false
        if(isOccupied(toPos, piece.getColor()) && !(piece instanceof King && ((King) piece).canCastle() && tempPiece instanceof Rook && ((Rook) tempPiece).canCastle()))
            return false;
        if (piece.getName().equals("chess.Pawn")) // special rules for pawns
        {
            Pawn p = (Pawn) piece;
            boolean val = p.isValidPawn(curPos, toPos, tempPiece, piece.getColor(), gameboard, previousMove);
            if (!val)
                return false;
//            p.movedtwo = true;
        }
        boolean castle=false;
        boolean longCastle=false;
        if (piece instanceof King) {
            King k = (King) piece;
            boolean val = k.isValidKing(curPos, toPos, this.gameboard, piece.getColor());
            if (!val && !isCastle)
                return false;
            if(tempPiece instanceof Rook){
                Rook r=(Rook)tempPiece;
                if(toPos.getCol()!=0){
                    longCastle=true;
                }
                //Checks if path is empty for castling
                if(!longCastle){
                    if(gameboard[curPos.getRow()][curPos.getCol()-1]!=null || gameboard[curPos.getRow()][curPos.getCol()-2]!=null){
                        return false;
                    }
                }
                else{
                    if(gameboard[curPos.getRow()][curPos.getCol()+1]!=null || gameboard[curPos.getRow()][curPos.getCol()+2]!=null || gameboard[curPos.getRow()][curPos.getCol()+3]!=null){
                        return false;
                    }
                }
                if(k.canCastle() && r.canCastle()){
                    castle=true;
                    ((King) piece).hasMoved = true;
                    ((Rook) tempPiece).hasMoved = true;
                }
                else{
                    return false;
                }
            }
        }
        if(!piece.isValidMove(curPos, toPos) && !castle) // Check if the move is theoretically possible
            return false;
        ChessPosition[] path = piece.getPath(curPos, toPos);
        if (isBlocked(path)) // If there is something in the way, move is illegal
            return false;
        // If you capture a piece, set its position to empty.
        if (tempPiece != null)
        {
            if (!(tempPiece.getColor().equals(piece.getColor())))
                tempPiece.setPosition(null);
        }
        // Actually moving the pieces
        if(!castle){
            gameboard[curPos.getRow()][curPos.getCol()] = null;
            gameboard[toPos.getRow()][toPos.getCol()] = piece;
        }
        else{
            gameboard[curPos.getRow()][curPos.getCol()] = null;
            gameboard[toPos.getRow()][toPos.getCol()] = null;
            if(!longCastle){
                curPos.setCol(curPos.getCol()-2);
                toPos.setCol(toPos.getCol()+2);
            }
            else{
                curPos.setCol(curPos.getCol()+2);
                toPos.setCol(toPos.getCol()-3);
            }
            gameboard[curPos.getRow()][curPos.getCol()] = piece;
            gameboard[toPos.getRow()][toPos.getCol()] = tempPiece;
            piece.setPosition(curPos);
            tempPiece.setPosition(toPos);
        }
        //
        if (piece instanceof Rook) {
            ((Rook) piece).hasMoved = true;
        }
        if (!castle)
            gameboard[toPos.getRow()][toPos.getCol()].setPosition(toPos);
        if (piece.getColor() == ChessColor.WHITE) // If the move results in a check for your color, it is illegal.
        {
            boolean val = discoveredChecks(ChessColor.BLACK); // returns true if there is a discovered check
            if (val && !castle) {
                if (piece instanceof Pawn) {
                    ((Pawn) piece).movedtwo = false;
                }
                // Change the piece back to its initial position (as if the move never occurred)
                gameboard[curPos.getRow()][curPos.getCol()] = piece;
                gameboard[toPos.getRow()][toPos.getCol()] = null;
                piece.setPosition(new ChessPosition(curPos.getRow(), curPos.getCol()));
                ChessPosition returnValue = new ChessPosition(toPos.getRow(), toPos.getCol());
                if (returnValue != null)
                {
                    if (piece instanceof Rook) {
                        ((Rook) piece).hasMoved = false;
                    }
                    if (piece instanceof King)
                        ((King) piece).hasMoved = false;
                    if (tempPiece != null)
                        tempPiece.setPosition(returnValue);
                }
                return false;
            }
            if (val && castle) {
                gameboard[curPos.getRow()][curPos.getCol()] = tempPiece;
                gameboard[curPos.getRow()][curPos.getCol()] = piece;
                tempPiece.setPosition(curPos);
                tempPiece.setPosition(toPos);
            }
        }
        this.previousMove = piece;
        // Same as what happens for white just if the Color is Black
        if (piece.getColor() == ChessColor.BLACK)
        {
            boolean val = discoveredChecks(ChessColor.WHITE);
            if (val) {
                if (piece instanceof Pawn) {
                    ((Pawn) piece).movedtwo = false;
                }
                gameboard[curPos.getRow()][curPos.getCol()] = piece;
                piece.setPosition(curPos);
                gameboard[toPos.getRow()][toPos.getCol()] = null;
                ChessPosition returnValue = new ChessPosition(toPos.getRow(), toPos.getCol());
                if (tempPiece != null)
                {
                    if (piece instanceof Rook) {
                        ((Rook) piece).hasMoved = false;
                    }
                    if (piece instanceof King)
                        ((King) piece).hasMoved = false;
                    tempPiece.setPosition(returnValue);
                }
                return false;
            }
        }
        if (piece instanceof Pawn) {
            if (((Pawn) piece).promoted) {
                gameboard[toPos.getRow()][toPos.getCol()] = end;
                for (int i = 0; i < whitepieces.length; i++) {
                    if (whitepieces[i].equals(piece)) {
                        whitepieces[i] = end;
                    }
                }
                end.setPosition(new ChessPosition(toPos.getRow(), toPos.getCol()));
            }
        }
        // If the move results in a check or a discovered check, there is a check
        if(isCheck(piece.getColor(), toPos) || discoveredChecks(piece.getColor())) {
            if (piece.getColor().equals(ChessColor.BLACK)) {
                // Before it was just boolean escapability = canEscape
                ChessPosition kingPos = null;
                int x = 0;
                for (int i = 0; i < whitepieces.length; i++) {
                    if (whitepieces[i] instanceof King) {
                        kingPos = whitepieces[i].getPosition();
                        x = i;
                    }
                }
                Chesspiece checkingPiece = null;
                for (int i = 0; i < blackpieces.length; i++) {
                    if (blackpieces[i].getCheck())
                        checkingPiece = blackpieces[i];
                }
                boolean escapability = canEscape(ChessColor.WHITE);
                for (int i = 0; i < blackpieces.length; i++) {
                    if (blackpieces[i] == checkingPiece) {
                        blackpieces[i].setCheck(true);
                    }
                    else
                        blackpieces[i].setCheck(false);
                }
                whitepieces[x].setPosition(kingPos);
                if (escapability) {
                    System.out.println("White is in Check!");
                    return true;
                }
                boolean blockability = canBlock(ChessColor.WHITE); // If they can block, they are not in checkmate
                if (!(escapability) && !(blockability)) {
                    System.out.println("Checkmate. \nBLACK WINS!");
                    whitecheckmate = true;
                }
                else {
                    System.out.println("White is in Check!");
                }
            }
            // Same rules for Black
            else {
                ChessPosition kingPos = null;
                int x = 0;
                for (int i = 0; i < blackpieces.length; i++) {
                    if (blackpieces[i] instanceof King) {
                        kingPos = blackpieces[i].getPosition();
                        x = i;
                    }
                }
                Chesspiece checkingPiece = null;
                for (int i = 0; i < whitepieces.length; i++) {
                    if (whitepieces[i].getCheck())
                        checkingPiece = whitepieces[i];
                }
                boolean escapability = canEscape(ChessColor.BLACK);
                for (int i = 0; i < whitepieces.length; i++) {
                    if (whitepieces[i] == checkingPiece) {
                        whitepieces[i].setCheck(true);
                    }
                    else
                        whitepieces[i].setCheck(false);
                }
                blackpieces[x].setPosition(kingPos);
                if (escapability) {
                    System.out.println("Black is in Check!");
                    return true;
                }
                else {
                    boolean blockability = canBlock(ChessColor.BLACK);
                    if (!(escapability) && !(blockability)) {
                        System.out.println("Checkmate. \nWHITE WINS!");
                        blackcheckmate = true;
                    } else {
                        System.out.println("Black is in Check!");
                    }
                }
            }

        }
        if (tempPiece != null) // if you actually capture a piece, set its position to null
        {
            if (!(tempPiece.getColor().equals(piece.getColor())))
            {
                tempPiece.setPosition(null);
            }
        }
        return true;
    }

    /**
     * Takes in the path of a piece to see if anything  is in the way of the move the piece is trying to do
     * @param path path of piece
     * @return true if blocked, else false
     */
    public boolean isBlocked(ChessPosition[] path) // checks if there is something in the way of the move
    {
        if (path == null)
            return false;
        for(int i = 0; i < path.length - 1; i++)
        {
            // first part of path is always the current piece.

            ChessPosition temp = path[i+1];
            if (temp != null)
            {
                if (gameboard[temp.getRow()][temp.getCol()] != null) // if it not null, as in there is a piece, the path is blocked
                    return true;
            }
        }
        return false;
    }

    /**
     * Takes in a chess position and a color to see if the ending square is occupied by a piece of the same color
     * @param pos position of piece
     * @param col color of piece
     * @return true if occupied, else false
     */
    public boolean isOccupied(ChessPosition pos, ChessColor col) // checks if the ending square is occupied by a same color piece
    {
        Chesspiece piece = gameboard[pos.getRow()][pos.getCol()];
        if (piece != null && piece.getColor().equals(col)) // if it is occupied by a piece of the same color, return false
            return true;
        else
            return false;

    }

    /**
     * Takes in a color and the end position to see if a move elicits a check
     * @param color color of piece
     * @param endPos end position of piece
     * @return true if in check, else false
     */
    public boolean isCheck(ChessColor color, ChessPosition endPos) // determines if there is a check
    {
        ChessPosition KingPos = null;
        if (color == ChessColor.WHITE)
        {
            for (int i = 0; i < blackpieces.length; i++)
            {
                if (blackpieces[i] != null)
                {
                    if (blackpieces[i].getName().equals("chess.King"))
                    {
                        KingPos = blackpieces[i].getPosition(); // gets the chess.King Position of Black
                    }
                }
            }
        }
        else {
            for (int i = 0; i < whitepieces.length; i++)
            {
                if (whitepieces[i] != null)
                {
                    if (whitepieces[i].getName().equals("chess.King"))
                    {
                        KingPos = whitepieces[i].getPosition(); // gets the chess.King Position of White
                    }
                }
            }
        }
        Chesspiece piece = gameboard[endPos.getRow()][endPos.getCol()];
        boolean ischeck = false;
        if (piece.isValidMove(endPos, KingPos)) // checks if there is a valid move between the piece that moved and the chess.King
        {
            ChessPosition[] checkpath = piece.getPath(endPos, KingPos);
            if (checkpath == null)
            {
                if (piece.getName().equals("chess.King")) // If you move a chess.King you cannot cause a check to the other color
                {
                    ischeck = false;
                }
                else if (piece.getName().equals("chess.Pawn")) // check if a pawn is causing a check
                {
                    if (piece.getColor() == ChessColor.WHITE)
                    {
                        if (endPos.getRow() + 1 < 8)
                        {
                            if (endPos.getCol() + 1 < 8)
                            {
                                if (gameboard[endPos.getRow() + 1][endPos.getCol() + 1] != null)
                                {
                                    if(gameboard[endPos.getRow() + 1][endPos.getCol() + 1].getName().equals("chess.King"))
                                    {
                                        ischeck = true;
                                        piece.setCheck(true);
                                    }
                                }
                            }
                            if (endPos.getCol() - 1 >= 0)
                            {
                                if (gameboard[endPos.getRow() + 1][endPos.getCol() - 1] != null)
                                {
                                    if(gameboard[endPos.getRow() + 1][endPos.getCol() - 1].getName().equals("chess.King"))
                                    {
                                        ischeck = true;
                                        piece.setCheck(true);
                                    }
                                }
                            }
                        }
                    }
                    else {
                        if (endPos.getRow() - 1 >= 0)
                        {
                            if (endPos.getCol() + 1 < 8)
                            {
                                if (gameboard[endPos.getRow() - 1][endPos.getCol() + 1] != null)
                                {
                                    if(gameboard[endPos.getRow() - 1][endPos.getCol() + 1].getName().equals("chess.King"))
                                    {
                                        ischeck = true;
                                        piece.setCheck(true);
                                    }
                                }
                            }
                            if (endPos.getCol() - 1 >= 0)
                            {
                                if (gameboard[endPos.getRow() - 1][endPos.getCol() + 1] != null)
                                {
                                    if(gameboard[endPos.getRow() - 1][endPos.getCol() - 1].getName().equals("chess.King"))
                                    {
                                        ischeck = true;
                                        piece.setCheck(true);
                                    }
                                }
                            }
                        }
                    }
                }
                else if (piece.getName().equals("chess.Knight")) // knights always cause a check if it is a valid move, they aren't blocked by anything
                {
                    boolean isKingIntheWay = piece.isValidMove(endPos, KingPos);
                    if (isKingIntheWay)
                    {
                        ischeck = true;
                        piece.setCheck(true);
                    }
                    else
                        ischeck = false;
                }
            }
            else{ // for other pieces, check if the path is blocked; if not, the piece is causing check
                ischeck = (!(isBlocked(checkpath)));
                if (ischeck == true)
                    piece.setCheck(true);
            }
        }
        else
            ischeck = false;
        return ischeck;
    }
//    public boolean checking(ChessColor c, ChessPosition endPos) {
//
//        return false;
//    }

    /**
     * Checks all pieces for discovered checks, as in that piece is not moving
     * @param color color of piece
     * @return true if check discovered, else false
     */
    public boolean discoveredChecks(ChessColor color)
    {
        // check all pieces for discovered checks, as in that piece is not moving
        ChessPosition KingPosition = null;
        if (color == ChessColor.WHITE)
        {
            for (int i = 0; i < blackpieces.length; i++)
            {
                if (blackpieces[i] != null)
                {
                    if (blackpieces[i].getName().equals("chess.King"))
                    {
                        KingPosition = blackpieces[i].getPosition(); // gets the position of the BLACK king
                    }

                }
            }
        }
        else {
            for (int i = 0; i < whitepieces.length; i++)
            {
                if (whitepieces[i].getName().equals("chess.King"))
                {
                    KingPosition = whitepieces[i].getPosition(); // gets the postion of the WHITE chess.King
                }
            }
        }
        boolean discover = true;
        // checks the path between all possible pieces and the chess.King, and if there is a path that is then returns true
        if (color == ChessColor.WHITE)
        {
            for (int i = 0; i < whitepieces.length; i++)
            {
                ChessPosition temp = whitepieces[i].getPosition();
                if (temp != null)
                {
                    Chesspiece piece = gameboard[temp.getRow()][temp.getCol()];
                    if (piece != null)
                    {
                        if (piece.isValidMove(piece.getPosition(), KingPosition))
                        {
                            if (piece instanceof Pawn) {
                                Pawn p = (Pawn) piece;
                                if (p.isValidPawn(p.getPosition(), KingPosition, null, ChessColor.WHITE, gameboard, previousMove)) {
                                    discover = false;
                                    continue;
                                }
                            }
                            ChessPosition[] checkpath = piece.getPath(temp, KingPosition);
                            if (checkpath != null)
                            {
                                for (int j = 0; j < checkpath.length - 1; j++)
                                {
                                    if (checkpath[j+1] != null)
                                    {
                                        ChessPosition tem = checkpath[j+1];
                                        if (gameboard[tem.getRow()][tem.getCol()] != null)
                                        {
                                            discover = false;
                                        }
                                    }
                                }
                            }
                            if (piece instanceof Knight) { // if it is a Valid Move for a chess.Pawn or chess.Knight to a chess.King, then it is a check.
                                piece.setCheck(true);
                                return true;
                            }
                            if (discover == true)
                            {
                                piece.setCheck(true);
                                return true;
                            }
                            discover = true;
                        }
                    }
                }
            }
        }
        // Do the same thing for black, as did for white
        else
        {
            for (int i = 0; i < blackpieces.length; i++)
            {
                ChessPosition temp = blackpieces[i].getPosition();
                if (temp != null)
                {
                    Chesspiece piece = gameboard[temp.getRow()][temp.getCol()];
                    if (piece != null)
                    {
                        if (piece.isValidMove(piece.getPosition(), KingPosition))
                        {
                            if (piece instanceof Pawn) {
                                Pawn p = (Pawn) piece;
                                if (p.isValidPawn(p.getPosition(), KingPosition, null, ChessColor.WHITE, gameboard, previousMove)) {
                                    discover = false;
                                    continue;
                                }
                            }
                            ChessPosition[] checkpath = piece.getPath(temp, KingPosition);
                            if (checkpath != null)
                            {
                                for (int j = 1; j < checkpath.length; j++)
                                {
                                    if (checkpath[j] != null)
                                    {
                                        ChessPosition tem = checkpath[j];
                                        if (gameboard[tem.getRow()][tem.getCol()] != null)
                                        {
                                            discover = false;
                                        }
                                    }
                                }
                            }
                            else if (piece instanceof Knight){ // if it is a Valid Move for a chess.Pawn or chess.Knight to a chess.King, then it is a check.
                                piece.setCheck(true);
                                return true;
                            }
                            if (discover == true)
                            {
                                piece.setCheck(true);
                                return true;
                            }
                            discover = true;
                        }

                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks to see if the king can escape to a different place if a check exists
     * @param color color of piece
     * @return true if you can escape, else false
     */
    public boolean canEscape(ChessColor color) { // Checks if the chess.King can escape to a different piece if there is a check
        ChessPosition KingPosition = null;
        Chesspiece kingpiece = null;
        if (color == ChessColor.WHITE)
        {
            for (int i = 0; i < whitepieces.length; i++)
            {
                if (whitepieces[i] != null)
                {
                    if (whitepieces[i].getName().equals("chess.King"))
                    {
                        KingPosition = whitepieces[i].getPosition(); // chess.King's Position
                        kingpiece = whitepieces[i]; // the actual chess.King piece
                    }
                }
            }
        }
        if (color == ChessColor.BLACK)
        {
            for (int i = 0; i < blackpieces.length; i++)
            {
                if (blackpieces[i] != null)
                {
                    if (blackpieces[i].getName().equals("chess.King"))
                    {
                        KingPosition = blackpieces[i].getPosition();
                        kingpiece = blackpieces[i];
                    }
                }
            }
        }
        ChessPosition[] possiblemoves = new ChessPosition[8]; // all the possible moves a chess.King can make (since a chess.King can only move to 8 squares at maximum)
        if (KingPosition.getRow() + 1 <= 7)
            possiblemoves[0] = new ChessPosition((KingPosition.getRow() + 1), (KingPosition.getCol()));
        if (KingPosition.getRow() - 1 >= 0)
            possiblemoves[1] = new ChessPosition(KingPosition.getRow() - 1, KingPosition.getCol());
        if (KingPosition.getCol() + 1 <= 7)
            possiblemoves[2] = new ChessPosition(KingPosition.getRow(), KingPosition.getCol() + 1);
        if (KingPosition.getCol() - 1 >= 0)
            possiblemoves[3] = new ChessPosition(KingPosition.getRow(), KingPosition.getCol() - 1);
        if (KingPosition.getRow() + 1 <= 7 && KingPosition.getCol() + 1 <= 7)
            possiblemoves[4] = new ChessPosition((KingPosition.getRow() + 1), (KingPosition.getCol() + 1));
        if (KingPosition.getRow() - 1 >= 0 && KingPosition.getCol() - 1 >= 0)
            possiblemoves[5] = new ChessPosition(KingPosition.getRow() - 1, KingPosition.getCol() - 1);
        if (KingPosition.getRow() + 1 <= 7 && KingPosition.getCol() - 1 >= 0)
            possiblemoves[6] = new ChessPosition((KingPosition.getRow() + 1), (KingPosition.getCol() - 1));
        if (KingPosition.getRow() - 1 >= 0 && KingPosition.getCol() + 1 <= 7)
            possiblemoves[7] = new ChessPosition(KingPosition.getRow() - 1, KingPosition.getCol() + 1);
        for (int i = 0; i < 8; i++) // Checks the validity to moving to each of those squares
        {
            if (possiblemoves[i] != null)
            {
                ChessPosition tempmove = possiblemoves[i];
                boolean val = kingpiece.isValidMove(KingPosition, possiblemoves[i]);
                if (val)
                {
                    if (gameboard[tempmove.getRow()][tempmove.getCol()] == null)
                    {
                        kingpiece.setPosition(possiblemoves[i]);
                        if (color == ChessColor.BLACK)
                        {
                            if (!(discoveredChecks(ChessColor.WHITE))) {
                                kingpiece.setPosition(KingPosition);
                                return true;
                            }
                        }
                        else {
                            if (!(discoveredChecks(ChessColor.BLACK))) {
                                kingpiece.setPosition(KingPosition);
                                return true;
                            }
                        }
                    }
                    else if(!(gameboard[tempmove.getRow()][tempmove.getCol()].getColor().equals(color)))
                    {
                        kingpiece.setPosition(possiblemoves[i]);
                        ChessPosition resPos = gameboard[tempmove.getRow()][tempmove.getCol()].getPosition();
                        gameboard[tempmove.getRow()][tempmove.getCol()].setPosition(null);
                        if (color == ChessColor.BLACK)
                        {
                            if (!(discoveredChecks(ChessColor.WHITE))) {
                                kingpiece.setPosition(KingPosition);
                                gameboard[tempmove.getRow()][tempmove.getCol()].setPosition(resPos);
                                return true;
                            }
                        }
                        else {
                            if (!(discoveredChecks(ChessColor.BLACK))) {
                                kingpiece.setPosition(KingPosition);
                                gameboard[tempmove.getRow()][tempmove.getCol()].setPosition(resPos);
                                return true;
                            }
                        }
                        kingpiece.setPosition(KingPosition);
                        gameboard[tempmove.getRow()][tempmove.getCol()].setPosition(resPos);
                    }
                }
            }
        }
        kingpiece.setPosition(KingPosition);
        return false;
    }

    /**
     * Checks to see if a piece can block the check
     * @param color color of piece
     * @return true if you can block, else false
     */
    public boolean canBlock(ChessColor color) // Checks if the check can be blocked
    {
        ChessPosition KingPosition = null;
        Chesspiece checkingpiece = null;
        if (color == ChessColor.BLACK)
        {
            for (int i = 0; i < blackpieces.length; i++)
            {
                if (blackpieces[i] != null)
                {
                    if (blackpieces[i].getName().equals("chess.King"))
                        KingPosition = blackpieces[i].getPosition();
                }
            }
            for (int i = 0; i < whitepieces.length; i++)
            {
                if (whitepieces[i] != null)
                {
                    if (whitepieces[i].getCheck() && whitepieces[i].getPosition() != null)
                        checkingpiece = whitepieces[i];
                }
            }
            ChessPosition[] checkingPath = checkingpiece.getPath(checkingpiece.getPosition(), KingPosition); // returns the path from the piece that is checking to the king
            if (checkingPath == null)
            {
                // only way to counter this is to capture the piece.
                for (int i = 0; i < blackpieces.length; i++)
                {
                    Chesspiece temp = blackpieces[i];
                    if (temp.getPosition() != null)
                    {
                        if (temp.isValidMove(temp.getPosition(), checkingpiece.getPosition()))
                        {
                            if (temp.getName().equals("chess.King"))
                            {
                                if (!(discoveredChecks(ChessColor.BLACK))) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            else // the path is not null
            {
                for (int i = 0; i < blackpieces.length; i++)
                {
                    Chesspiece temp = blackpieces[i];
                    // Hey
                    for (int j = 0; j < checkingPath.length; j++)
                    {
                        if (temp.getPosition() != null && checkingPath[j] != null) {
                            if (checkingPath[j] != null) {
                                if (temp.isValidMove(temp.getPosition(), checkingPath[j])) // checks the validity of a move to each of the positions on the path
                                {
                                    ChessPosition[] path = temp.getPath(temp.getPosition(), checkingPath[j]);
                                    if (path != null) {
                                        if (!(this.isBlocked(path))) // if it is possible to block a position on the path, the check can be blocked {
                                            return true;
                                    } else {
                                        if (blackpieces[i].getName().equals("chess.Pawn")) {
                                            Pawn p = (Pawn) blackpieces[i];
                                            if (p.isValidPawn(temp.getPosition(), checkingPath[j], null, ChessColor.BLACK, gameboard, previousMove)) // special rules for a pawn
                                                return true;
                                        }
                                        if (blackpieces[i].getName().equals("chess.Knight")) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // the same rules apply, but for the opposite color
        else {
            for (int i = 0; i < whitepieces.length; i++)
            {
                if (whitepieces[i] != null)
                {
                    if (whitepieces[i].getName().equals("chess.King"))
                        KingPosition = whitepieces[i].getPosition();
                }
            }
            for (int i = 0; i < blackpieces.length; i++)
            {
                if (blackpieces[i] != null)
                {
                    if (blackpieces[i].getCheck())
                        checkingpiece = blackpieces[i];
                }
            }
            ChessPosition[] checkingPath = checkingpiece.getPath(checkingpiece.getPosition(), KingPosition);
            if (checkingPath == null)
            {
                // only way to counter this is to capture the piece.
                for (int i = 0; i < blackpieces.length; i++)
                {
                    Chesspiece temp = blackpieces[i];
                    if (temp.getPosition() != null)
                    {
                        if (temp.isValidMove(temp.getPosition(), checkingpiece.getPosition()))
                        {
                            if (temp.getName().equals("chess.King"))
                            {
                                if (!(discoveredChecks(ChessColor.BLACK)))
                                    return true;
                            }
                        }
                    }
                }
            }
            else {
                for (int i = 0; i < whitepieces.length; i++)
                {
                    Chesspiece temp = whitepieces[i];
                    if (temp.getPosition() != null)
                    {
                        for (int j = 0; j < checkingPath.length; j++) {
                            if (checkingPath[j] != null) {
                                if (temp.isValidMove(temp.getPosition(), checkingPath[j])) {
                                    ChessPosition[] path = temp.getPath(temp.getPosition(), checkingPath[j]);
                                    if (path != null) {
                                        if (!(this.isBlocked(path)))
                                            return true;
                                    } else {
                                        if (whitepieces[i].getName().equals("chess.Pawn")) {
                                            Pawn p = (Pawn) whitepieces[i];
                                            if (p.isValidPawn(temp.getPosition(), checkingPath[j], null, ChessColor.WHITE, gameboard, previousMove))
                                                return true;
                                        }
                                        if (whitepieces[i].getName().equals("chess.Knight")) {
                                            return true;
                                        }
//                                        if (whitepieces[i] instanceof King) {
//                                            if (!discoveredChecks(ChessColor.WHITE))
//                                                return true;
//                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    private void revertPawns() {
        for (int i = 0; i < blackpieces.length; i++) {
            if (blackpieces[i] != null) {
                if (blackpieces[i] instanceof Pawn) {
                    Pawn p = (Pawn) blackpieces[i];
                    p.movedtwo = false;
                }
            }
        }
        for (int i = 0; i < whitepieces.length; i++) {
            if (whitepieces[i] != null) {
                if (whitepieces[i] instanceof Pawn) {
                    Pawn p = (Pawn) whitepieces[i];
                    p.movedtwo = false;
                }
            }
        }
    }

    /**
     * Displays the board onto the terminal
     */
    public void display() // display the board on the terminal
    {
        // TODO: Flip the hashtags to the other diagonals
        int count = 0;
        for (int i = 7; i >= 0; i--)
        {
            for (int j = 7; j >= 0; j--)
            {
                if (gameboard[i][j] == null) {
                    if ((i+j) % 2 == 0) {
                        if (j == 7) {
                            System.out.print("  ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    else {
                        if (j == 7) {
                            System.out.print("##");
                        }
                        else {
                            System.out.print(" ##");
                        }
                    }
                }
                else if (gameboard[i][j].getName().equals("chess.Rook") && (gameboard[i][j].getColor() == ChessColor.WHITE)) {
                    if (j == 7)
                        System.out.print("wR");
                    else
                        System.out.print(" wR");
                }
                else if (gameboard[i][j].getName().equals("chess.Knight") && (gameboard[i][j].getColor() == ChessColor.WHITE)) {
                    if (j == 7)
                        System.out.print("wN");
                    else
                        System.out.print(" wN");
                }
                else if (gameboard[i][j].getName().equals("chess.Pawn") && (gameboard[i][j].getColor() == ChessColor.WHITE)) {
                    if (j == 7)
                        System.out.print("wp");
                    else
                        System.out.print(" wp");
                }
                else if (gameboard[i][j].getName().equals("chess.Queen") && (gameboard[i][j].getColor() == ChessColor.WHITE)) {
                    if (j == 7)
                        System.out.print("wQ");
                    else
                        System.out.print(" wQ");
                }
                else if (gameboard[i][j].getName().equals("chess.King") && (gameboard[i][j].getColor() == ChessColor.WHITE)) {
                    if (j == 7)
                        System.out.print("wK");
                    else
                        System.out.print(" wK");
                }
                else if (gameboard[i][j].getName().equals("chess.Bishop") && (gameboard[i][j].getColor() == ChessColor.WHITE)) {
                    if (j == 7)
                        System.out.print("wB");
                    else
                        System.out.print(" wB");
                }
                else if (gameboard[i][j].getName().equals("chess.Rook") && (gameboard[i][j].getColor() == ChessColor.BLACK)) {
                    if (j == 7)
                        System.out.print("bR");
                    else
                        System.out.print(" bR");
                }
                else if (gameboard[i][j].getName().equals("chess.Knight") && (gameboard[i][j].getColor() == ChessColor.BLACK)) {
                    if (j == 7)
                        System.out.print("bN");
                    else
                        System.out.print(" bN");
                }
                else if (gameboard[i][j].getName().equals("chess.Pawn") && (gameboard[i][j].getColor() == ChessColor.BLACK)) {
                    if (j == 7)
                        System.out.print("bp");
                    else
                        System.out.print(" bp");
                }
                else if (gameboard[i][j].getName().equals("chess.Queen") && (gameboard[i][j].getColor() == ChessColor.BLACK)) {
                    if (j == 7)
                        System.out.print("bQ");
                    else
                        System.out.print(" bQ");
                }
                else if (gameboard[i][j].getName().equals("chess.King") && (gameboard[i][j].getColor() == ChessColor.BLACK)) {
                    if (j == 7)
                        System.out.print("bK");
                    else
                        System.out.print(" bK");
                }
                else if (gameboard[i][j].getName().equals("chess.Bishop") && (gameboard[i][j].getColor() == ChessColor.BLACK)) {
                    if (j == 7)
                        System.out.print("bB");
                    else
                        System.out.print(" bB");
                }
            }
            int val = 8 + count;
            System.out.print(" " + val);
            System.out.print("\n");
            count--;
        }
        System.out.println(" a  b  c  d  e  f  g  h");
    }
}