package Reinforcement;

/**
 * Created by roye1 on 2017/8/16.
 */
public class RuleJudger {


    public boolean isActionValid(int []board,Action action)
    {
        if(board[action.getActionNum()]!=0)
        {
            return false;
        }
        return true;
    }
    int []getNextBoard(int []board,Action action,int pieceColor)
    {
        int []nextBoard=board.clone();
        nextBoard[action.getActionNum()]=pieceColor;
        return nextBoard;
    }
    public int isFinished(int []board)
    {
        //-1 沒輸沒贏
        //0 繼續下
        //1黑贏
        //2白贏
        int []currentBoard=board.clone();
        int winner=-1;
        for(int i=0;i<currentBoard.length;i++)
        {
            if(currentBoard[i]==0)
            {
                winner=0;
            }
        }
        for(int i=0;i<3;i++)
        {
            if(currentBoard[i]==currentBoard[i+3]&&currentBoard[i]==currentBoard[i+6])
            {
                if(currentBoard[i]==1)
                {
                    winner=1;
                }
                if(currentBoard[i]==2)
                {
                    winner=2;
                }
            }
        }
        for(int i=0;i<9;i+=3)
        {
            if(currentBoard[i]==currentBoard[i+1]&&currentBoard[i]==currentBoard[i+2])
            {
                if(currentBoard[i]==1)
                {
                    winner=1;
                }
                if(currentBoard[i]==2)
                {
                    winner=2;
                }
            }
        }
        if(currentBoard[0]==currentBoard[4]&&currentBoard[0]==currentBoard[8])
        {
            if(currentBoard[0]==1)
            {
                winner=1;
            }
            if(currentBoard[0]==2)
            {
                winner=2;
            }
        }
        if(currentBoard[2]==currentBoard[4]&&currentBoard[2]==currentBoard[6])
        {
            if(currentBoard[2]==1)
            {
                winner=1;
            }
            if(currentBoard[2]==2)
            {
                winner=2;
            }
        }
        return winner;
    }
}
