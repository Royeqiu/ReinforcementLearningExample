package Reinforcement;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by roye1 on 2017/7/24.
 */
public class ReinforcementCalculator {

    Random rand=new Random(3);
    double jumpProb=0.05;
    double alpha=0.5;//learning rate
    double lambda=0.9;
    static int maxActionNum=2;
    static int maxStateNum=15;
    int episode=50;
    ArrayList<Action> actions;
    ArrayList<State> states;
    State currentState;
    State nextState;
    StateActionPair currentStateActionPair;
    boolean isFinish;
    QTable qTable;
    public ReinforcementCalculator()
    {
        actions=new ArrayList<>();
        states=new ArrayList<>();
        currentStateActionPair=new StateActionPair();
        isFinish=false;
        nextState=null;
        init();
        qTable=new QTable(states,actions);

    }
    public void start()
    {
        int step;
        for(int i=0;i<episode;i++)
        {
            step=0;
            isFinish=false;
            currentState=states.get(0);
            currentStateActionPair.setState(currentState);
            while(!isFinish)
            {
                double currentReward=chooseAction();
                double terminalReward=getFeedBack();
                double nextActionReward=getNextActionReward(nextState);
                double predictReward=nextActionReward;
                double updateReward=currentReward+alpha*(terminalReward+lambda*predictReward-currentReward);
                updateQTable(currentStateActionPair, updateReward);
                updateEnvironment();
                step++;
            }
            System.out.println("Round "+i+"  cost step: "+step+"  :get the terminal!");

        }
        qTable.showQTable();
    }

    void init()
    {
        loadState();
        loadAction();
        currentState=states.get(0);
        currentStateActionPair.setState(currentState);

    }
    protected void loadAction()
    {
        System.out.println("@@");
        String actionName;
        for(int i=0;i<maxActionNum;i++)
        {
            if(i==0)
            {
                actionName="left";
            }
            else
            {
                actionName="right";
            }
            actions.add(new Action(actionName,i));
        }
    }
    protected void loadState()
    {

        for(int i=0;i<maxStateNum;i++)
        {
           states.add(new State(Integer.toString(i),i));
        }
    }
    double chooseAction()
    {

        double maxScore=0;

        Action actionIndex=null;
        if(rand.nextDouble()>jumpProb)
        {
            for(Action action:actions)
            {
                currentStateActionPair.setAction(action);
                if(qTable.getActionScore(currentStateActionPair)>maxScore)
                {
                    maxScore=qTable.getActionScore(currentStateActionPair);
                    actionIndex=action;
                }
            }
            if(maxScore==0)
            {
                actionIndex=actions.get(rand.nextInt(maxActionNum));
                currentStateActionPair.setAction(actionIndex);
            }
            else
            {
                currentStateActionPair.setAction(actionIndex);
            }
        }
        else
        {
            actionIndex=actions.get(rand.nextInt(maxActionNum));
            currentStateActionPair.setAction(actionIndex);
        }
        return qTable.getActionScore(currentStateActionPair);
    }
    private State getNextState(StateActionPair currentStateActioniPair)
    {
        int stateIndex=currentStateActioniPair.getState().getStateNum();
        int actionIndex=currentStateActioniPair.getAction().getActionNum();
        if(actionIndex==0)
        {
            stateIndex-=1;
            if(stateIndex==-1)
            {
                stateIndex=0;
            }
        }
        else
        {
            stateIndex+=1;
        }
        return states.get(stateIndex);
    }
    double getFeedBack()
    {
        nextState=getNextState(currentStateActionPair);
        if(nextState.getStateNum()==maxStateNum-1)
        {
            isFinish=true;
            return 1;
        }
        else
        {
            return 0;
        }
    }
    double getNextActionReward(State state)
    {
        double maxScore=0;
        StateActionPair stateActionPair=new StateActionPair();
        stateActionPair.setState(state);
        Action actionIndex=null;
        for(Action action:actions)
        {
            stateActionPair.setAction(action);
            if(qTable.getActionScore(stateActionPair)>=maxScore)
            {
                maxScore=qTable.getActionScore(stateActionPair);
                actionIndex=action;
            }
        }
        stateActionPair.setAction(actionIndex);
        return qTable.getActionScore(stateActionPair);
    }
    void updateQTable(StateActionPair stateActionPair,double updateReward)
    {
        qTable.updateScore(stateActionPair,updateReward);
    }
    private void updateEnvironment()
    {
        currentState=nextState;
        currentStateActionPair.setState(currentState);
    }
}

