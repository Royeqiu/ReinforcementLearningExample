package Reinforcement;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by roye1 on 2017/7/24.
 */
public class QTable {

    Hashtable<StateActionPair,Double> actionScore;
    QTable(ArrayList<State> states,ArrayList<Action> actions)
    {
        initQTable(states,actions);
    }
    void initQTable(ArrayList<State> states,ArrayList<Action> actions)
    {
        actionScore=new Hashtable<>();
        for(State state:states)
        {
            for(Action action:actions)
            {
                actionScore.put(new StateActionPair(state,action),0.0);
            }
        }
    }
    void showQTable()
    {
        for(StateActionPair stateActionPair:actionScore.keySet())
        {
            System.out.println(stateActionPair.toString()+":"+actionScore.get(stateActionPair));
        }
    }
    double getActionScore(StateActionPair stateActionPair)
    {
        return actionScore.get(stateActionPair);
    }
    void updateScore(StateActionPair stateActionPair,double updateScore)
    {
        actionScore.put(stateActionPair,updateScore);
    }
}
