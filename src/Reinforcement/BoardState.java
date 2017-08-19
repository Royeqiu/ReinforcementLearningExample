package Reinforcement;

/**
 * Created by roye1 on 2017/8/16.
 */
public class BoardState extends State {
    int []board;
    public BoardState(String stateName, int stateNum, int[] board) {
        super(stateName, stateNum);
        init(board);

    }
    public BoardState() {
        super();
    }
    public void setBoard(int[] board) {
        this.board=new int[board.length];
        for(int i=0;i<this.board.length;i++)
        {
            this.board[i]=board[i];
        }
    }


    void init(int []board)
    {
        this.board=new int[board.length];
        for(int i=0;i<this.board.length;i++)
        {
            this.board[i]=board[i];
        }
    }
    int[] getBoard()
    {
        return board;
    }

    @Override
    public int hashCode() {
        String boardString="";
        for(int i=0;i<board.length;i++)
        {
            boardString+=board[i]+"";
        }
        return boardString.hashCode();
    }
    public String toString()
    {
        String boardString="";
        for(int i=0;i<board.length;i++)
        {
            boardString+=board[i]+"";
        }
        return stateName+" "+stateNum+" "+boardString;
    }
    public boolean equals(Object obj)
    {
        if(obj.getClass().equals(this.getClass()))
        {
            BoardState boardStateObj=(BoardState)obj;
            int board[]=boardStateObj.getBoard();
            for(int i=0;i<board.length;i++)
            {
                if(board[i]!=this.board[i])
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
