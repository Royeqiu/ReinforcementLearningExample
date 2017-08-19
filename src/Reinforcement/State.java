package Reinforcement;

import java.util.Hashtable;

/**
 * Created by roye1 on 2017/7/24.
 */
public class State {
    String stateName;
    int stateNum;
    State(String stateName,int stateNum)
    {
        this.stateName=stateName;
        this.stateNum=stateNum;
    }
    State()
    {

    }
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getStateNum() {
        return stateNum;
    }

    public void setStateNum(int stateNum) {
        this.stateNum = stateNum;
    }
    public String toString()
    {
        return stateName+" "+stateNum;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass()))
        {
            State stateObj=(State)obj;
            if(stateObj.getStateName().equals(this.stateName)&&stateObj.getStateNum()==this.stateNum)
            {
                return true;
            }
        }
        return false;
    }
}
