package Reinforcement;

/**
 * Created by roye1 on 2017/8/16.
 */
public class HumanTranslator {

    public State getCurrentState(int board[])
    {
        BoardState currentState=new BoardState();
        currentState.setBoard(board);
        return currentState;
    }
    public Action getCurrentActioin(int actionNum)
    {
        Action action=new Action(Integer.toString(actionNum),actionNum);
        return action;
    }
}
