package Reinforcement;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by roye1 on 2017/8/16.
 */
public class TicTacToePlayer extends ReinforcementCalculator{
    public Hashtable<State,Integer> boardStateSet;
    RuleJudger ruleJudger=new RuleJudger();
    int pieceColor;
    int competitorColor;
    double jumpProb=0.5;
    public TicTacToePlayer()
    {
        init();
    }
    void init()
    {
        maxActionNum=9;
        actions=new ArrayList<>();
        states=new ArrayList<>();
        boardStateSet=new Hashtable<>();

        loadAction();
    }
    public void initPlayer(int []initialBoardState,int pieceColor)
    {
        this.pieceColor=pieceColor;
        if(pieceColor==1)
        {
            competitorColor=2;
        }
        else
        {
            competitorColor=1;
        }
        BoardState tempBoardState=new BoardState("0",0,initialBoardState);
        if(!boardStateSet.containsKey(tempBoardState))
        {
            states.add(tempBoardState);
            boardStateSet.put(tempBoardState,boardStateSet.size());
        }
        currentState=tempBoardState;
        currentStateActionPair.setState(tempBoardState);
    }
    public int[] getCurrentBoard()
    {
        BoardState currentBoardState=(BoardState)currentState;
        return currentBoardState.getBoard();
    }
    public void showQTable()
    {
        for(StateActionPair stateActionPair:qTable.getActionScore().keySet())
        {
            BoardState boardState=(BoardState) stateActionPair.getState();
            Action action=stateActionPair.getAction();
            System.out.println(boardState.toString()+" "+action.toString()+" : "+qTable.getActionScore(stateActionPair));
        }
        System.out.println(qTable.getActionScore().size());
    }
    public Action movePiece(int []board)
    {
        double maxReward=0;

        for(Action action:actions)
        {
            StateActionPair tempStateActionPair=new StateActionPair();
            tempStateActionPair.setState(currentStateActionPair.getState());
            tempStateActionPair.setAction(action);
            if(!qTable.containsKey(tempStateActionPair))
            {
                double stateActionScore=0;
                if(!ruleJudger.isActionValid(board,action))
                {
                    stateActionScore=-1;
                }
               qTable.updateScore(tempStateActionPair,stateActionScore);
            }

            double actionReward=qTable.getActionScore(tempStateActionPair);
            if(actionReward>maxReward)
            {
                maxReward=actionReward;
                currentStateActionPair.setAction(action);
            }
        }


        if(maxReward==0||rand.nextDouble()>jumpProb)
        {
            int randomActionIndex=0;
            BoardState currentBoardState=(BoardState) currentState;
            do
            {
            randomActionIndex=rand.nextInt(maxActionNum);
            }while(!ruleJudger.isActionValid(board,actions.get(randomActionIndex)));

            currentStateActionPair.setAction(actions.get(randomActionIndex));

        }

        return currentStateActionPair.getAction();
    }

    public void update(boolean isfinished,int currentBoard[])
    {

        if(!qTable.containsKey(currentStateActionPair))
        {
            qTable.updateScore(currentStateActionPair,0);
        }
        double currentActionReward=qTable.getActionScore(currentStateActionPair);

        double nextActionReward=0;
        if(!isfinished)
        {
            nextActionReward=getNextActionReward(currentBoard);
        }
        double terminalReward=getFeedBack(currentBoard);
        double updateReward=currentActionReward+alpha*(terminalReward+lambda*nextActionReward-currentActionReward);

        updateQTable(currentStateActionPair,updateReward);
        updateEnvironment();
    }
    private void updateEnvironment()
    {
        currentState=nextState;
        currentStateActionPair.setState(currentState);
    }
    public void deJump()
    {
        if(jumpProb>0.05)
        {
            jumpProb-=0.05;
        }

    }
    private double getNextActionReward(int []nextBoard)
    {
        BoardState nextBoardState=new BoardState("",0,nextBoard);
        if(!states.contains(nextBoardState))
        {
            nextBoardState.setStateNum(states.size());
            nextBoardState.setStateName(Integer.toString(states.size()));
            states.add(nextBoardState);
        }
        else
        {
            nextBoardState=(BoardState) states.get(states.indexOf(nextBoardState));
        }
        StateActionPair tempStateActionPair=new StateActionPair();
        tempStateActionPair.setState(nextBoardState);
        nextState=nextBoardState;
        tempStateActionPair.setState(nextState);
        double maxReward=0;
        for(Action action:actions)
        {
            tempStateActionPair.setAction(action);
            if(!qTable.containsKey(tempStateActionPair))
            {
                qTable.updateScore(tempStateActionPair,0);
            }
            if(qTable.getActionScore(tempStateActionPair)>maxReward)
            {
                maxReward=qTable.getActionScore(tempStateActionPair);
            }
        }
        return maxReward;
    }

    private double getFeedBack(int[] board)
    {
        double terminalReward=0;

        int winner=ruleJudger.isFinished(board);
        if(winner==0)
        {
            terminalReward=0;
        }
        else if(winner==this.pieceColor)
        {
            terminalReward=1;
        }
        else if(winner==-1)
        {
            terminalReward=1;
        }
        else
        {
            terminalReward=0;
        }
        return terminalReward;
    }
    protected void loadAction()
    {
        for(int i=0;i<maxActionNum;i++)
        {
            actions.add(new Action(Integer.toString(i),i));
        }
    }
}
